package org.reclipse.structure.inference.ui.views.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.fujaba.commons.console.ProcessConsoleState;
import org.reclipse.metamodel.AbstractElementLabeler;
import org.reclipse.metamodel.MetaModel;
import org.reclipse.structure.inference.InferenceEngine;
import org.reclipse.structure.inference.annotations.ASGAnnotation;
import org.reclipse.structure.inference.notification.InferenceProgressListener;
import org.reclipse.structure.inference.notification.InferenceProgressProvider;
import org.reclipse.structure.specification.PSPatternSpecification;
import org.reclipse.structure.specification.util.ModelHelper;

public class AnnotationView extends ViewPart implements InferenceProgressListener {

    public static final String ID = "org.reclipse.ui.views.annotations"; //$NON-NLS-1$

    private final Map<PSPatternSpecification, Set<ASGAnnotation>> annotations;

    private final Map<EObject, Collection<ASGAnnotation>> annotatedByCache;

    private AbstractElementLabeler labeler;

    private boolean showLongNames;

    private TreeViewer viewer;

    private Label progressLabel;

    private ProgressBar progressBar;

    private AnnotationsViewerFilter filter;

    private AnnotationsViewerComparator comparator;

    private AnnotationsTreeLabelProvider labelProvider;

    public AnnotationView() {
        super();

        this.showLongNames = false;
        this.annotations = new HashMap<PSPatternSpecification, Set<ASGAnnotation>>();
        this.annotatedByCache = new HashMap<EObject, Collection<ASGAnnotation>>();
    }

    private void addAnnotation(final ASGAnnotation annotation) {
        if (this.labeler == null) {
            final MetaModel mm = ModelHelper.getMetaModel(annotation.getPattern());
            this.labeler = mm.getLabeler();
        }

        // get pattern
        final PSPatternSpecification pattern = annotation.getPattern();

        // get existing annotations
        Set<ASGAnnotation> existing = this.annotations.get(pattern);
        if (existing == null) {
            // create new set
            existing = new HashSet<ASGAnnotation>();
            this.annotations.put(pattern, existing);
        }

        // add the annotation
        existing.add(annotation);
    }

    @Override
    public void createPartControl(final Composite parent) {
        // create providers
        this.labelProvider = new AnnotationsTreeLabelProvider(this);
        this.comparator = new AnnotationsViewerComparator(this.labelProvider);
        this.filter = new AnnotationsViewerFilter();

        // main composite
        final Composite main = new Composite(parent, SWT.NONE);
        GridLayoutFactory.fillDefaults().applyTo(main);

        // create tree
        final Tree tree = new Tree(main, SWT.FULL_SELECTION | SWT.MULTI);
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);
        GridDataFactory.fillDefaults().grab(true, true).applyTo(tree);

        // create viewer
        this.viewer = new TreeViewer(tree);
        this.viewer.setContentProvider(new AnnotationsTreeContentProvider(this));
        this.viewer.setFilters(new ViewerFilter[] { this.filter });
        this.viewer.setComparator(this.comparator);
        this.viewer.setLabelProvider(this.labelProvider);

        // create columns
        final TreeColumn annoColumn = new TreeColumn(tree, SWT.LEAD);
        annoColumn.setText("Annotation");
        annoColumn.setWidth(300);
        annoColumn.setMoveable(true);
        annoColumn.setResizable(true);
        annoColumn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                AnnotationView.this.comparator.setColumn(0);
                final int dir = AnnotationView.this.comparator.getDirection();
                AnnotationView.this.viewer.getTree().setSortDirection(dir);
                AnnotationView.this.viewer.refresh();
            }
        });

        final TreeColumn ratingColumn = new TreeColumn(tree, SWT.LEAD);
        ratingColumn.setText("Rating");
        ratingColumn.setWidth(52);
        ratingColumn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                AnnotationView.this.comparator.setColumn(1);
                final int dir = AnnotationView.this.comparator.getDirection();
                AnnotationView.this.viewer.getTree().setSortDirection(dir);
                AnnotationView.this.viewer.refresh();
            }
        });

        final TreeColumn elementsColumn = new TreeColumn(tree, SWT.LEAD);
        elementsColumn.setText("Annotated Elements");
        elementsColumn.setWidth(500);
        elementsColumn.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                AnnotationView.this.comparator.setColumn(2);
                final int dir = AnnotationView.this.comparator.getDirection();
                AnnotationView.this.viewer.getTree().setSortDirection(dir);
                AnnotationView.this.viewer.refresh();
            }
        });

        // set sort column
        tree.setSortDirection(SWT.UP);
        tree.setSortColumn(annoColumn);

        // status
        final Composite state = new Composite(main, SWT.BORDER);
        GridLayoutFactory.fillDefaults().spacing(5, 5).applyTo(state);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(state);

        this.progressLabel = new Label(state, SWT.LEAD);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(this.progressLabel);

        this.progressBar = new ProgressBar(state, SWT.SMOOTH);
        GridDataFactory.fillDefaults().grab(true, false).applyTo(this.progressBar);

        // register viewer as selection provider
        getSite().setSelectionProvider(this.viewer);

        createContextMenu();
    }

    public Collection<ASGAnnotation> getAnnotatedBy(final EObject element, final ASGAnnotation hide) {
        // get from cache
        Collection<ASGAnnotation> annotatedBy = this.annotatedByCache.get(element);

        if (annotatedBy == null) {
            // create list
            annotatedBy = new ArrayList<ASGAnnotation>();

            // go through all patterns
            for (final Set<ASGAnnotation> annotationList : this.annotations.values()) {
                // go through all annotations
                for (final ASGAnnotation annotation : annotationList) {
                    // filter itself
                    if (!hide.equals(annotation)) {
                        // go through all annotated keys
                        final Collection<String> keys = annotation.getAnnotatedElements().keySet();
                        for (final String key : keys) {
                            // get annotated elements
                            final Collection<EObject> annotatedList = annotation.getAnnotatedElements().get(key);
                            for (final EObject annotated : annotatedList) {
                                if (element.equals(annotated)) {
                                    annotatedBy.add(annotation);
                                }
                            }
                        }
                    }
                }
            }

            // push it into the cache
            this.annotatedByCache.put(element, annotatedBy);
        }

        return annotatedBy;
    }

    public String getAnnotatedText(final ASGAnnotation annotation) {
        // create text
        final StringBuilder text = new StringBuilder();

        // get keys
        final Collection<String> keys = annotation.getAnnotatedElements().keySet();
        int keyCount = 1;
        int empties = keys.size();
        for (final String key : keys) {
            // get elements
            final Collection<EObject> annotated = annotation.getAnnotatedElements().get(key);

            // check
            if (annotated != null && !annotated.isEmpty()) {
                text.append(key);
                text.append("="); //$NON-NLS-1$
                int annotatedCount = 1;
                for (final EObject element : annotated) {
                    text.append(getText(element));
                    if (annotatedCount < annotated.size()) {
                        text.append("; "); //$NON-NLS-1$
                    }
                    annotatedCount++;
                }
            } else {
                empties--;
            }

            if (keyCount < empties) {
                text.append(", "); //$NON-NLS-1$
            }
            keyCount++;
        }

        return text.toString();
    }

    public Map<PSPatternSpecification, Set<ASGAnnotation>> getAnnotations() {
        return this.annotations;
    }

    public Image getImage(final EObject element) {
        if (this.labeler == null) {
            return null;
        }

        return this.labeler.getImage(element);
    }

    public String getText(final AnnotatedContainer annotated) {
        final StringBuilder text = new StringBuilder();

        text.append("["); //$NON-NLS-1$
        int keyCount = 1;
        for (final String key : annotated.getKeys()) {
            text.append(key);
            if (keyCount < annotated.getKeys().size()) {
                text.append(", "); //$NON-NLS-1$
            }
            keyCount++;
        }
        text.append("] "); //$NON-NLS-1$

        text.append(getText(annotated.getElement()));

        return text.toString();
    }

    private String getText(final EObject element) {
        if (this.labeler == null) {
            return element.toString();
        }

        if (this.showLongNames) {
            return this.labeler.getFullText(element);
        } else {
            return this.labeler.getText(element);
        }
    }

    public boolean isEmpty() {
        return this.annotations == null || this.annotations.isEmpty();
    }

    public boolean isVisible(final ASGAnnotation element) {
        return false;
    }

    public void loadAnnotations(final Collection<ASGAnnotation> annos) {
        // clear the existing
        this.annotations.clear();

        // collect the annotations
        for (final ASGAnnotation annotation : annos) {
            addAnnotation(annotation);
        }

        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                // fully progressed
                AnnotationView.this.progressBar.setState(SWT.NORMAL);
                AnnotationView.this.progressBar.setMinimum(0);
                AnnotationView.this.progressBar.setMaximum(1);
                AnnotationView.this.progressBar.setSelection(1);

                // fill viewer
                AnnotationView.this.viewer.setInput(AnnotationView.this.annotations);
                AnnotationView.this.viewer.refresh();

                // show loaded state
                AnnotationView.this.progressLabel
                        .setText("Loaded " + annos.size() + " annotations successfully from resource.");
            }
        });
    }

    private void refresh() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (!AnnotationView.this.viewer.getControl().isDisposed()) {
                    AnnotationView.this.viewer.refresh();
                }
            }
        });
    }

    public void setFiltering(final int state) {
        switch (state) {
        case 0:
        default:
            this.filter.showAll();
            break;
        case 1:
            this.filter.showOnlyAntecendents();
            break;
        case 2:
            this.filter.showOnlyConsequents();
            break;
        }

        refresh();
    }

    public void setFiltering(final Map<String, Double> filtering) {
        // create returning list
        final Collection<ASGAnnotation> hiding = new ArrayList<ASGAnnotation>();

        // get annotation lists
        final Set<PSPatternSpecification> patterns = this.annotations.keySet();

        // go through those lists
        for (final PSPatternSpecification pattern : patterns) {
            // check name
            if (filtering.containsKey(pattern.getName())) {
                // get filtering value
                final double filter = filtering.get(pattern.getName());

                // get annotations
                final Set<ASGAnnotation> list = this.annotations.get(pattern);

                // go through the annotations
                for (final ASGAnnotation anno : list) {
                    // get the rating
                    final double rating = anno.getAnnotationRanking();

                    if (filter == -1 || rating < filter) {
                        hiding.add(anno);
                    }
                }
            }
        }

        this.filter.hideAnnotations(hiding);
        refresh();
    }

    @Override
    public void setFocus() {
        this.viewer.getControl().setFocus();
    }

    public void setProgress(final int current, final int maximum) {
        final int percent = current * 100 / (maximum != 0 ? maximum : 1);

        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                AnnotationView.this.progressBar.setSelection(current);
                AnnotationView.this.progressBar.setMaximum(maximum);
                AnnotationView.this.progressLabel.setText(
                        "Processed " + percent + "% - " + current + " of " + maximum + " required analysis steps.");
            }
        });
    }

    public void setProgressState(final int state) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                if (!AnnotationView.this.progressBar.isDisposed()) {
                    AnnotationView.this.progressBar.setState(state);
                }

                if (state == SWT.ERROR) {
                    AnnotationView.this.progressLabel
                            .setText("<Terminated> " + AnnotationView.this.progressLabel.getText());
                }
            }
        });
    }

    public void switchToInference(final InferenceEngine inference) {
        // reset
        this.annotatedByCache.clear();
        this.annotations.clear();
        this.labeler = null;

        // add listener
        InferenceProgressProvider.get(inference).addListener(this);

        // fill viewer
        this.viewer.setInput(this.annotations);

        // label
        this.progressLabel.setText(""); //$NON-NLS-1$

        // bar
        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(1);
        this.progressBar.setSelection(0);
        this.progressBar.setState(SWT.NORMAL);

        refresh();
    }

    public void toggleNames() {
        this.showLongNames = !this.showLongNames;
        refresh();
    }

    private void createContextMenu() {
        final MenuManager menuMgr = new MenuManager();

        // Create menu.
        final Menu menu = menuMgr.createContextMenu(this.viewer.getControl());
        this.viewer.getControl().setMenu(menu);

        // Register menu for extension.
        getSite().registerContextMenu(menuMgr, this.viewer);
    }

    @Override
    public void newState(final ProcessConsoleState state) {
        switch (state) {
        case ABORTED:
            setProgressState(SWT.ERROR);
            refresh();
            break;

        case PAUSED:
            setProgressState(SWT.PAUSED);
            refresh();
            break;

        case IDLING:
        case RUNNING:
            setProgressState(SWT.NORMAL);
            break;

        case FINISHED:
            setProgressState(SWT.NORMAL);
            refresh();
            break;

        default:
            break;
        }
    }

    @Override
    public void init() {
        this.annotations.clear();
        refresh();
    }

    @Override
    public void newValues(final int current, final int maximum) {
        setProgress(current, maximum);
    }

    @Override
    public void newAnnotation(final ASGAnnotation annotation) {
        addAnnotation(annotation);
    }

    public void select(final ASGAnnotation oldAnnotation, final ASGAnnotation newAnnotation) {
        // select the current annotation
        this.viewer.setSelection(new StructuredSelection(oldAnnotation));

        // expand it
        this.viewer.setExpandedState(oldAnnotation, true);

        // work with the tree directly :-(
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                // the current annotation tree item
                final TreeItem oldItem = AnnotationView.this.viewer.getTree().getSelection()[0];

                // go through its children
                for (final TreeItem child : oldItem.getItems()) {
                    if (child.getData() instanceof AntecedentContainer) {
                        // expand the antecedent container
                        AnnotationView.this.viewer.setExpandedState(child.getData(), true);

                        // go through its children to find the new annotation
                        for (final TreeItem subChild : child.getItems()) {
                            if (newAnnotation.equals(subChild.getData())) {
                                // select the new annotation
                                AnnotationView.this.viewer.getTree().showItem(subChild);
                                AnnotationView.this.viewer.getTree().select(subChild);
                            }
                        }
                    }
                }
            }
        });
        setFocus();
    }
}
