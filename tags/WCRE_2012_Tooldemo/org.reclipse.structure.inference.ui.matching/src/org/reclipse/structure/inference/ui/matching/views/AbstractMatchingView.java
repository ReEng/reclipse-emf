package org.reclipse.structure.inference.ui.matching.views;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.part.ViewPart;
import org.fujaba.commons.console.ProcessConsoleState;
import org.fujaba.commons.edit.parts.AbstractEdgeEditPart;
import org.fujaba.commons.edit.parts.AbstractNodeEditPart;
import org.fujaba.commons.notation.DiagramElement;
import org.fujaba.commons.notation.HierarchicalNode;
import org.fujaba.commons.ui.WorkbenchFileTreeSelectionDialog;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.notification.InferenceProgressListener;
import org.reclipse.structure.inference.ui.views.annotations.AnnotationView;
import org.reclipse.structure.specification.PSPatternSpecification;

/**
 * @version $Revision$ $Date$
 * @author Last editor: $Author$
 * @author harka
 */
public abstract class AbstractMatchingView extends ViewPart implements
		ISelectionListener, InferenceProgressListener {

	private static final String MODEL_EXT = ".psc";

	private static final String DIAGRAM_EXT = ".pscdiagram";

	/**
	 * Holds the edit domain for all matching views.
	 */
	private static EditDomain editDomain;

	private static ASGAnnotation current;

	public static final ASGAnnotation getCurrent() {
		return current;
	}

	private static final EditDomain getEditDomain() {
		// lazy initialize 'global' edit domain
		if (editDomain == null) {
			editDomain = new EditDomain();
		}

		return editDomain;
	}

	public static void setLayout(IWorkbenchPart part, HierarchicalNode root,
			boolean all) {
		// clear cache of one part
		if (part instanceof ObjectMatchingView) {
			if (all) {
				ObjectMatchingView.getAnnotationCache().clear();
			}

			ObjectMatchingView.getPatternCache().put(getCurrent().getPattern(),
					root);
		} else if (part instanceof PatternMatchingView) {
			if (all) {
				PatternMatchingView.getAnnotationCache().clear();
			}

			PatternMatchingView.getPatternCache().put(
					getCurrent().getPattern(), root);
		}
	}

	private ScrollingGraphicalViewer viewer;

	protected void addSelection(DiagramElement select) {
		Object part = getViewer().getEditPartRegistry().get(select);

		if (part != null) {
			if (part instanceof AbstractNodeEditPart) {
				((EditPart) part).setSelected(EditPart.SELECTED);
			} else if (part instanceof AbstractEdgeEditPart) {
				((EditPart) part).setSelected(EditPart.SELECTED);
			}
		} else {
			// search adequate part
			for (Object potential : getViewer().getEditPartRegistry().values()) {
				if (potential instanceof AbstractNodeEditPart) {
					if (((AbstractNodeEditPart) potential).getRealModel()
							.equals(select.getModel())) {
						((EditPart) potential).setSelected(EditPart.SELECTED);
					}
				}
			}
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		// configure viewer
		viewer = new ScrollingGraphicalViewer();
		viewer.createControl(parent);
		viewer.setEditPartFactory(getEditPartFactory());
		viewer.setEditDomain(getEditDomain());

		MenuManager contextMenuManager = createContextMenuManager(viewer);
		if (contextMenuManager != null) {
			viewer.setContextMenu(contextMenuManager);
			getSite().registerContextMenu(getId(), contextMenuManager, viewer);
		}

		// hear on new selection
		getSite().getPage().addSelectionListener(this);

		// provide selection information
		getSite().setSelectionProvider(getViewer());

		// initialize the selection
		selectionChanged(getSite().getPart(), getSite().getPage()
				.getSelection());
	}

	protected abstract String getId();

	protected MenuManager createContextMenuManager(
			ScrollingGraphicalViewer viewer) {
		return null;
	}

	@Override
	public void dispose() {
		// remove selection listener
		getSite().getPage().removeSelectionListener(this);

		// dispose viewer
		if (viewer.getControl() != null) {
			viewer.getControl().dispose();
		}
		viewer = null;

		super.dispose();
	}

	/**
	 * Returns the pattern specification diagram node corresponding to the given
	 * annotation.
	 * 
	 * @param anno
	 *            The annotation.
	 * @return Returns the pattern specification diagram node corresponding to
	 *         the given annotation.
	 */
	private HierarchicalNode getDiagramRoot(ASGAnnotation anno) {
		HierarchicalNode node = null;

		// get diagram root for the annotation
		if (this instanceof ObjectMatchingView) {
			node = ObjectMatchingView.getAnnotationCache().get(anno);
		} else if (this instanceof PatternMatchingView) {
			node = PatternMatchingView.getAnnotationCache().get(anno);
		}

		// when not already cached, create a new one
		if (node == null) {
			// get it from cached patterns
			if (this instanceof ObjectMatchingView) {
				node = ObjectMatchingView.getPatternCache().get(
						anno.getPattern());
			} else if (this instanceof PatternMatchingView) {
				node = PatternMatchingView.getPatternCache().get(
						anno.getPattern());
			}

			// when not already cached, create it from existing diagram
			if (node == null) {
				// cache some
				PSPatternSpecification pattern = anno.getPattern();
				Resource res = pattern.eResource();
				ResourceSet ress = res.getResourceSet();
				String path = res.getURI().toPlatformString(false)
						.replace(MODEL_EXT, DIAGRAM_EXT);

				// try to load diagram with same file name
				node = getDiagramRootFromExisting(ress, pattern, path);

				// wrong input or file not exists
				if (node == null) {
					// open dialog as long as no diagram exists
					MessageDialog dialog;
					String extension = DIAGRAM_EXT.substring(1);
					boolean aborted = false;
					while (node == null && !aborted) {
						// get result of the dialog
						dialog = getDiagramSelectionDialog(path,
								pattern.getName());

						switch (dialog.open()) {
						case 0:
						default:
							// select a new resource
							ElementTreeSelectionDialog selector = new WorkbenchFileTreeSelectionDialog(
									getSite().getShell(),
									"Pattern Specification Diagram",
									"Select a structural pattern specification diagram.",
									extension);

							if (selector.open() == Window.OK) {
								IFile selected = (IFile) selector
										.getFirstResult();
								path = selected.getFullPath().toOSString();
							}
							break;

						case 1:
							// TODO: auto create diagram
							System.out.println("TODO: auto create diagram");
							break;

						case 2:
							aborted = true;
							break;
						}

						node = getDiagramRootFromExisting(ress, pattern, path);
					}
				}

				// cache found pattern diagram node
				if (this instanceof ObjectMatchingView) {
					ObjectMatchingView.getPatternCache().put(pattern, node);
				} else if (this instanceof PatternMatchingView) {
					PatternMatchingView.getPatternCache().put(pattern, node);
				}
			}

			// copy from cached pattern
			if (this instanceof ObjectMatchingView) {
				node = EcoreUtil.copy(ObjectMatchingView.getPatternCache().get(
						anno.getPattern()));
			} else if (this instanceof PatternMatchingView) {
				node = EcoreUtil.copy(PatternMatchingView.getPatternCache()
						.get(anno.getPattern()));
			}

			// save diagram root for the annotation
			if (this instanceof ObjectMatchingView) {
				ObjectMatchingView.getAnnotationCache().put(anno, node);
			} else if (this instanceof PatternMatchingView) {
				PatternMatchingView.getAnnotationCache().put(anno, node);
			}
		}

		return node;
	}

	private HierarchicalNode getDiagramRootFromExisting(ResourceSet ress,
			PSPatternSpecification pattern, String path) {
		// create resource
		URI uri = URI.createPlatformResourceURI(path, true);
		Resource res = ress.getResource(uri, true);

		// load
		if (!res.isLoaded()) {
			try {
				res.load(Collections.emptyMap());
			} catch (IOException e) {
				return null;
			}
		}

		// search adequate element
		for (EObject content : res.getContents()) {
			if (content instanceof HierarchicalNode) {
				if (((HierarchicalNode) content).getModel().equals(pattern)) {
					return (HierarchicalNode) content;
				}
			}
		}

		return null;
	}

	private MessageDialog getDiagramSelectionDialog(String path, String name) {
		// prepare dialog texts
		String m = "There is no diagram representation for the Pattern Specification '"
				+ name + "' in the file '" + path + "'.";
		String[] l = new String[] { "Select Another Diagram",
				"Create Automatically", "Abort" };

		return new MessageDialog(getSite().getShell(),
				"Missing Pattern Specification Diagram", getTitleImage(), m,
				MessageDialog.QUESTION, l, 0);
	}

	/**
	 * Creates and returns the edit part factory to be used with this view.
	 * Subclasses have to implement this method.
	 * 
	 * @return Returns the edit part factory to be used for this view.
	 */
	protected abstract EditPartFactory getEditPartFactory();

	private ScrollingGraphicalViewer getViewer() {
		return viewer;
	}

	protected void removeSelection() {
		for (Object part : getViewer().getEditPartRegistry().values()) {
			if (part instanceof EditPart) {
				((EditPart) part).setSelected(EditPart.SELECTED_NONE);
			}
		}
	}

	protected void selectEditParts(StructuredSelection selection) {
		// remove old selection
		removeSelection();

		// add selection to the edit parts
		for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
			Object s = iterator.next();

			// check for correct edit part
			if (s instanceof AbstractNodeEditPart) {
				// get real element
				addSelection(((AbstractNodeEditPart) s).getModel());
			} else if (s instanceof AbstractEdgeEditPart) {
				// get real element
				addSelection(((AbstractEdgeEditPart) s).getModel());
			}
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// abort on bad selection
		if (selection == null || selection.isEmpty()
				|| !(selection instanceof StructuredSelection)) {
			return;
		}

		// selected from a matching view
		if (part instanceof AbstractMatchingView) {
			selectEditParts((StructuredSelection) selection);

			return;
		}

		// selected from annotation view
		if (part instanceof AnnotationView) {
			Object selected = ((StructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof ASGAnnotation) {
				setContent((ASGAnnotation) selected);
			}

			return;
		}
	}

	private void setContent(ASGAnnotation selected) {
		AbstractMatchingView.current = selected;
		getViewer().setContents(getDiagramRoot(selected));
	}

	@Override
	public void setFocus() {
		// sets the focus to the viewer
		viewer.getControl().setFocus();
	}

	@Override
	public void newValues(int current, int maximum) {
		// do nothing on new values
	}

	@Override
	public void newAnnotation(ASGAnnotation annotation) {
		// do nothing on a new annotation
	}

	@Override
	public void newState(ProcessConsoleState state) {
		// nothing
	}

	@Override
	public void init() {
		// clear viewer
		if (viewer != null && !viewer.getControl().isDisposed()) {
			viewer.setContents(null);
		}

		clear();
	}

	protected abstract void clear();
}
