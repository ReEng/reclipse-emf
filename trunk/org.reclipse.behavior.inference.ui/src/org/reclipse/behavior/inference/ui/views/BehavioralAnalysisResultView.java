package org.reclipse.behavior.inference.ui.views;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.reclipse.behavior.inference.Annotation;
import org.reclipse.behavior.inference.BehavioralAnnotation;
import org.reclipse.behavior.inference.StructuralAnnotation;
import org.reclipse.behavior.inference.Trace;
import org.reclipse.behavior.inference.ui.BehavioralInferenceImages;
import org.reclipse.tracer.model.tracegraph.TGObject;

import de.uni_paderborn.basicSequenceDiagram.SequenceDiagram;
import de.uni_paderborn.sequencediagram.viewer.editparts.SequenceDiagramEditPart;
import de.uni_paderborn.sequencediagram.viewer.editparts.SequenceDiagramEditPartFactory;


/**
 * @author lowende
 * @author Last editor: $Author: mcp $
 * @version $Revision: 4624 $ $Date: 2011-01-18 10:58:31 +0100 (Di, 18. Jan 2011) $
 */
public class BehavioralAnalysisResultView extends ViewPart implements SelectionListener
{

   public static String ID = "org.reclipse.ui.views.behavior.inference";

   private Combo annotationsCombo;

   private TableViewer boundNodesTableViewer;

   private Combo tracesCombo;

   private TableViewer boundObjectsTableViewer;

   private ScrollingGraphicalViewer graphicalViewer;

   private SequenceDiagramEditPart sequenceDiagramEditPart;

   private Map<String, Annotation> annotations;

   private Map<String, Trace> traces;

   private Composite composite;

   private Label fuzzyBeliefLabel;

   private Label tracesLabel;

   private Label notAcceptedLabel;

   private Label acceptedLabel;

   private Label rejectedLabel;

   private Label passedAcceptingStateTracesLabel;

   private Label avgLengthLabel;

   private Label resultImageLabel;

   private Label resultLabel;

   private Label symbolicExecutionLabel;

   private Label symbolicExecutionInterpretationImage;

   private Label symbolicExecutionInterpretationLabel;


   public BehavioralAnalysisResultView()
   {
      this.annotations = new HashMap<String, Annotation>();
      this.traces = new HashMap<String, Trace>();

      // TODO
      // Register UMLProjectFactory
      // ProjectManager projectManager = ProjectManager.get();
      // FProjectFactory<UMLProject> factory = projectManager
      // .getFromProjectFactories(UMLProject.class);
      // if (factory == null)
      // {
      // factory = new UMLProjectFactory();
      // projectManager.addToProjectFactories(UMLProject.class, factory);
      // }
      //
      // this.project = factory.create(null, false);
      // UMLPackage root = this.project.getFromFactories(UMLPackage.class).create(false);
      // this.project.setRootPackage(root);
   }


   /**
    * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
    */
   @Override
   public void createPartControl(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 2;

      this.composite = new Composite(parent, SWT.NONE);
      this.composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      this.composite.setLayoutData(gridData);

      FillLayout fillLayout = new FillLayout();
      fillLayout.marginHeight = 3;
      fillLayout.marginWidth = 5;

      Group group = new Group(this.composite, SWT.NONE);
      group.setLayout(fillLayout);
      group.setText("Design Pattern Candidates");

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalSpan = 2;
      group.setLayoutData(gridData);

      this.annotationsCombo = new Combo(group, SWT.DROP_DOWN | SWT.READ_ONLY);
      this.annotationsCombo.setToolTipText("Select an annotation created by" + " the design pattern recognition.");
      this.annotationsCombo.addSelectionListener(this);

      createStructuralAnalysisPart(this.composite);
      createBehavioralAnalysisPart(this.composite);
      createSequenceDiagramPart(this.composite);

      contributeToActionBars();
   }


   private void createStructuralAnalysisPart(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 2;

      Group group = new Group(parent, SWT.SHADOW_NONE);
      group.setText("Structural Analysis Results");
      group.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      group.setLayoutData(gridData);

      Label label = new Label(group, SWT.NONE);
      label.setText("Fuzzy Belief:");

      this.fuzzyBeliefLabel = new Label(group, SWT.NONE);
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.horizontalAlignment = SWT.FILL;
      this.fuzzyBeliefLabel.setLayoutData(gridData);

      this.boundNodesTableViewer = new TableViewer(group, SWT.H_SCROLL | SWT.BORDER | SWT.HIDE_SELECTION);
      this.boundNodesTableViewer.setContentProvider(new BoundNodesTableContentProvider());
      this.boundNodesTableViewer.setLabelProvider(new BoundNodesTableLabelProvider());

      Table table = this.boundNodesTableViewer.getTable();
      table.setHeaderVisible(true);
      table.setLinesVisible(true);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalSpan = 2;
      gridData.heightHint = 72;
      table.setLayoutData(gridData);

      TableColumn column = new TableColumn(table, SWT.NONE);
      column.setText("Structural Pattern Variable");
      column.setWidth(145);

      column = new TableColumn(table, SWT.NONE);
      column.setText("Structural Model Element");
      column.setWidth(165);
   }


   private void createBehavioralAnalysisPart(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginWidth = 0;
      gridLayout.numColumns = 3;

      Group group = new Group(parent, SWT.SHADOW_NONE);
      group.setText("Behavioral Analysis Results");
      group.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      group.setLayoutData(gridData);

      createBehavioralAnnotationPart(group);

      Label separator = new Label(group, SWT.SEPARATOR | SWT.VERTICAL);
      gridData = new GridData();
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessVerticalSpace = true;
      separator.setLayoutData(gridData);

      createTracePart(group);
   }


   private void createBehavioralAnnotationPart(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.numColumns = 3;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessVerticalSpace = true;
      composite.setLayoutData(gridData);

      this.createSymbolicExecutionPart(composite);

      Label label = new Label(composite, SWT.NONE);
      label.setText("Method call sequences:");
      label.setToolTipText("Number of monitored method call sequences.");

      this.tracesLabel = new Label(composite, SWT.NONE);
      this.tracesLabel.setToolTipText("Number of monitored method call sequences.");

      gridData = new GridData();
      gridData.horizontalSpan = 2;
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.tracesLabel.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label.setImage(BehavioralInferenceImages.getImage(BehavioralInferenceImages.IMG_ACCEPTED));
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.CENTER;
      label.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label.setText("accepted:");
      label.setToolTipText("Number of accepted method call sequences.");

      this.acceptedLabel = new Label(composite, SWT.NONE);
      this.acceptedLabel.setToolTipText("Number of accepted method call sequences.");
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.widthHint = 15;
      this.acceptedLabel.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label.setImage(BehavioralInferenceImages.getImage(BehavioralInferenceImages.IMG_REJECTED));
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.CENTER;
      label.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label.setText("rejected:");
      label.setToolTipText("Number of rejected method call sequences.");

      this.rejectedLabel = new Label(composite, SWT.NONE);
      this.rejectedLabel.setToolTipText("Number of rejected method call sequences.");
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.widthHint = 15;
      this.rejectedLabel.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label.setImage(BehavioralInferenceImages.getImage(BehavioralInferenceImages.IMG_NOT_ACCEPTED));
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.CENTER;
      label.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label.setText("not accepted:");
      label.setToolTipText("Number of not accepted method call sequences.");

      this.notAcceptedLabel = new Label(composite, SWT.NONE);
      this.notAcceptedLabel.setToolTipText("Number of not accepted method call sequences.");
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.widthHint = 15;
      this.notAcceptedLabel.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label = new Label(composite, SWT.NONE);
      label.setText("accepted subsequences:");
      label.setToolTipText("Number of rejected and not accepted "
            + "method call sequences that contain accepted subsequences.");

      this.passedAcceptingStateTracesLabel = new Label(composite, SWT.NONE);
      this.passedAcceptingStateTracesLabel.setToolTipText("Number of rejected"
            + " and not accepted method call sequences that contain accepted subsequences.");
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.widthHint = 15;
      this.passedAcceptingStateTracesLabel.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label = new Label(composite, SWT.NONE);
      label.setText("average length of");
      label.setToolTipText("Average number of method calls of" + " accepted subsequences.");

      gridData = new GridData();
      gridData.verticalIndent = 2;
      gridData.horizontalSpan = 2;
      label.setLayoutData(gridData);

      label = new Label(composite, SWT.NONE);
      label = new Label(composite, SWT.NONE);
      label.setText("accepted (sub-)sequences:");
      label.setToolTipText("Average number of method calls of" + " accepted subsequences.");

      gridData = new GridData();
      gridData.horizontalIndent = 5;
      gridData.verticalIndent = 0;
      label.setLayoutData(gridData);

      this.avgLengthLabel = new Label(composite, SWT.NONE);
      this.avgLengthLabel.setToolTipText("Average number of method calls of" + " accepted subsequences.");
      gridData = new GridData();
      gridData.grabExcessHorizontalSpace = true;
      gridData.widthHint = 20;
      this.avgLengthLabel.setLayoutData(gridData);
   }


   private void createSymbolicExecutionPart(Composite parent)
   {
      Label label = new Label(parent, SWT.NONE);
      label.setText("Symbolic Execution: ");

      this.symbolicExecutionLabel = new Label(parent, SWT.NONE);
      this.symbolicExecutionLabel.setText("No");
      this.symbolicExecutionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

      label = new Label(parent, SWT.NONE);
      label.setText("Interpretation: ");

      this.symbolicExecutionInterpretationLabel = new Label(parent, SWT.NONE);
      this.symbolicExecutionInterpretationLabel.setText("-");
      this.symbolicExecutionInterpretationLabel
            .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

      this.symbolicExecutionInterpretationImage = new Label(parent, SWT.NONE);
      this.symbolicExecutionInterpretationImage.setImage(BehavioralInferenceImages
            .getImage(BehavioralInferenceImages.IMG_NOT_ACCEPTED));
      this.symbolicExecutionInterpretationImage
            .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

      Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
      separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
   }


   private void createTracePart(Composite parent)
   {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;

      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(gridLayout);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      composite.setLayoutData(gridData);

      this.tracesCombo = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
      this.tracesCombo.setToolTipText("Select a matched method call sequence.");
      this.tracesCombo.addSelectionListener(this);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.tracesCombo.setLayoutData(gridData);

      Composite resultComposite = new Composite(composite, SWT.NONE);
      GridLayout resultCompositeLayout = new GridLayout();
      resultCompositeLayout.numColumns = 2;
      resultCompositeLayout.marginHeight = 0;
      resultCompositeLayout.marginWidth = 0;
      resultComposite.setLayout(resultCompositeLayout);

      this.resultImageLabel = new Label(resultComposite, SWT.NONE);
      gridData = new GridData();
      gridData.heightHint = 16;
      gridData.widthHint = 16;
      this.resultImageLabel.setLayoutData(gridData);

      this.resultLabel = new Label(resultComposite, SWT.WRAP);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      this.resultLabel.setLayoutData(gridData);

      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      resultComposite.setLayoutData(gridData);

      this.boundObjectsTableViewer = new TableViewer(composite, SWT.H_SCROLL | SWT.BORDER | SWT.HIDE_SELECTION);
      this.boundObjectsTableViewer.setContentProvider(new BoundObjectsTableContentProvider());
      this.boundObjectsTableViewer.setLabelProvider(new BoundObjectsTableLabelProvider());

      Table table = this.boundObjectsTableViewer.getTable();
      table.setHeaderVisible(true);
      table.setLinesVisible(true);
      gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.heightHint = 72;
      table.setLayoutData(gridData);

      TableColumn column = new TableColumn(table, SWT.NONE);
      column.setText("Behavioral Pattern Object");
      column.setWidth(140);

      column = new TableColumn(table, SWT.NONE);
      column.setText("Instance");
      column.setWidth(60);
   }


   private void createSequenceDiagramPart(Composite parent)
   {
      GridData gridData = new GridData();
      gridData.horizontalAlignment = SWT.FILL;
      gridData.verticalAlignment = SWT.FILL;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalSpan = 2;

      FillLayout fillLayout = new FillLayout();
      fillLayout.marginHeight = 5;
      fillLayout.marginWidth = 5;

      Group sequenceDiagramGroup = new Group(parent, SWT.NONE);
      sequenceDiagramGroup.setText("Method Call Sequence Diagram");
      sequenceDiagramGroup.setLayoutData(gridData);
      sequenceDiagramGroup.setLayout(fillLayout);

      // TODO?
      this.sequenceDiagramEditPart = new SequenceDiagramEditPart();

      this.graphicalViewer = new ScrollingGraphicalViewer();
      this.graphicalViewer.createControl(sequenceDiagramGroup);
      this.graphicalViewer.setEditPartFactory(new SequenceDiagramEditPartFactory());

      EditDomain editDomain = new EditDomain();
      editDomain.setActiveTool(new SelectionTool());
      this.graphicalViewer.setEditDomain(editDomain);
   }


   private void contributeToActionBars()
   {
      // OpenBehavioralAnalysisResultAction action = new OpenBehavioralAnalysisResultAction(
      // this);
      //
      // IActionBars actionBars = getViewSite().getActionBars();
      // IToolBarManager toolBarManager = actionBars.getToolBarManager();
      // toolBarManager.add(action);
   }


   /**
    * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
    */
   @Override
   public void setFocus()
   {
      this.composite.setFocus();
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetDefaultSelected(SelectionEvent event)
   {
   }


   /**
    * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
    */
   public void widgetSelected(SelectionEvent event)
   {
      Object source = event.getSource();
      if (source == this.annotationsCombo)
      {
         int selectedIndex = this.annotationsCombo.getSelectionIndex();
         String key = this.annotationsCombo.getItem(selectedIndex);
         Annotation annotation = this.annotations.get(key);
         updateStructuralAnnotation(annotation.getStructuralAnnotation());
         updateBehavioralAnnotation(annotation.getBehavioralAnnotation());
      }
      else if (source == this.tracesCombo)
      {
         int selectedIndex = this.tracesCombo.getSelectionIndex();
         String key = this.tracesCombo.getItem(selectedIndex);
         Trace trace = this.traces.get(key);
         updateTrace(trace);
      }
   }


   public void setModel(Set<Annotation> model)
   {
      if (model == null)
      {
         return;
      }

      this.annotations.clear();
      for (Annotation annotation : model)
      {
         this.annotations.put(annotation.toString(), annotation);
      }

      List<String> list = new ArrayList<String>();
      list.addAll(this.annotations.keySet());
      Collections.sort(list);
      this.annotationsCombo.setItems(list.toArray((String[]) Array.newInstance(String.class, list.size())));
      if (list.size() > 0)
      {
         this.annotationsCombo.select(0);

         Annotation annotation = this.annotations.get(list.get(0));
         updateStructuralAnnotation(annotation.getStructuralAnnotation());
         updateBehavioralAnnotation(annotation.getBehavioralAnnotation());
      }
   }


   private void updateStructuralAnnotation(StructuralAnnotation structuralAnnotation)
   {
      this.fuzzyBeliefLabel.setText(String.format("%1$.2f", structuralAnnotation.getFuzzyBelief()) + "%");
      this.boundNodesTableViewer.setInput(structuralAnnotation);
   }


   private void updateBehavioralAnnotation(BehavioralAnnotation behavioralAnnotation)
   {
      if (behavioralAnnotation.getSymbolicExecution())
      {
         this.symbolicExecutionLabel.setText("Yes");
         if (behavioralAnnotation.interpretSymbolicTracePaths() == BehavioralAnnotation.TRUE_POSITIVE)
         {
            this.symbolicExecutionInterpretationLabel.setText("True Positive");
            this.symbolicExecutionInterpretationImage.setImage(BehavioralInferenceImages
                  .getImage(BehavioralInferenceImages.IMG_ACCEPTED));
         }
         else
         {
            this.symbolicExecutionInterpretationLabel.setText("False Positive");
            this.symbolicExecutionInterpretationImage.setImage(BehavioralInferenceImages
                  .getImage(BehavioralInferenceImages.IMG_REJECTED));
         }
      }
      else
      {
         this.symbolicExecutionLabel.setText("No");
         this.symbolicExecutionInterpretationLabel.setText("-");
         this.symbolicExecutionInterpretationImage.setImage(BehavioralInferenceImages
               .getImage(BehavioralInferenceImages.IMG_REJECTED));
      }


      this.tracesLabel.setText(Integer.toString(behavioralAnnotation.sizeOfTraces()));
      this.notAcceptedLabel.setText(Integer.toString(behavioralAnnotation.getNotAcceptedTraces()));
      this.acceptedLabel.setText(Integer.toString(behavioralAnnotation.getAcceptedTraces()));
      this.rejectedLabel.setText(Integer.toString(behavioralAnnotation.getRejectedTraces()));
      this.passedAcceptingStateTracesLabel.setText(Integer.toString(behavioralAnnotation
            .getPassedAcceptingStateTraces()));
      this.avgLengthLabel.setText(Float.toString(behavioralAnnotation.getAvgLengthAcceptedTraces()));

      updateTracesCombo(behavioralAnnotation);
   }


   private void updateTracesCombo(BehavioralAnnotation behavioralAnnotation)
   {
      this.traces.clear();

      Iterator<Trace> traceIter = behavioralAnnotation.iteratorOfTraces();
      while (traceIter.hasNext())
      {
         Trace trace = traceIter.next();
         this.traces.put("Method call sequence " + trace.getId(), trace);
      }

      List<String> list = new ArrayList<String>(this.traces.keySet());
      Collections.sort(list);
      this.tracesCombo.setItems(list.toArray((String[]) Array.newInstance(String.class, list.size())));
      if (list.size() > 0)
      {
         this.tracesCombo.select(0);
         updateTrace(this.traces.get(list.get(0)));
      }
   }


   private void updateTrace(Trace trace)
   {
      Image image;
      StringBuffer buffer = new StringBuffer();
      if (trace.getResult() == Trace.NOT_ACCEPTED)
      {
         image = BehavioralInferenceImages.getImage(BehavioralInferenceImages.IMG_NOT_ACCEPTED);
         buffer.append("Method call sequence was not accepted");

         if (trace.getPassedAcceptingState() == 1)
         {
            buffer.append(", passed 1 accepting state," + " length of accepted subsequence: ");
            buffer.append(trace.getLengthOfAcceptedTrace());
         }
         else if (trace.getPassedAcceptingState() > 1)
         {
            buffer.append(", passed ");
            buffer.append(trace.getPassedAcceptingState());
            buffer.append(" accepting states, length of accepted subsequence: ");
            buffer.append(trace.getLengthOfAcceptedTrace());
         }
      }
      else if (trace.getResult() == Trace.ACCEPTED)
      {
         image = BehavioralInferenceImages.getImage(BehavioralInferenceImages.IMG_ACCEPTED);
         buffer.append("Method call sequence was accepted, length of sequence: ");
         buffer.append(trace.getLengthOfAcceptedTrace());
      }
      else
      {
         image = BehavioralInferenceImages.getImage(BehavioralInferenceImages.IMG_REJECTED);
         buffer.append("Method call sequence was rejected");

         if (trace.getPassedAcceptingState() == 1)
         {
            buffer.append(", passed 1 accepting state," + " length of accepted subsequence: ");
            buffer.append(trace.getLengthOfAcceptedTrace());
         }
         else if (trace.getPassedAcceptingState() > 1)
         {
            buffer.append(", passed ");
            buffer.append(trace.getPassedAcceptingState());
            buffer.append(" accepting states, length of accepted subsequence: ");
            buffer.append(trace.getLengthOfAcceptedTrace());
         }
      }
      this.resultImageLabel.setImage(image);
      this.resultLabel.setText(buffer.toString());

      this.boundObjectsTableViewer.setInput(trace);

      updateSequenceDiagram(trace);
   }


   private void updateSequenceDiagram(Trace trace)
   {
      SequenceDiagram sequenceDiagram = SequenceDiagramGenerator.getSequenceDiagram(trace);
      this.sequenceDiagramEditPart.setModel(sequenceDiagram);
      this.sequenceDiagramEditPart.setParent(this.graphicalViewer.getRootEditPart());
      this.graphicalViewer.setContents(sequenceDiagram);
      // this.sequenceDiagramEditPart.activate();
      // this.sequenceDiagramEditPart.relayout();
   }


   class BoundNodesTableContentProvider implements IStructuredContentProvider
   {

      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(Object inputElement)
      {
         StructuralAnnotation structuralAnnotation = (StructuralAnnotation) inputElement;
         return structuralAnnotation.entrySetOfNodes().toArray();
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      }

   }


   class BoundNodesTableLabelProvider implements ITableLabelProvider
   {

      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(Object element, int columnIndex)
      {
         Entry<String, String> entry = (Entry<String, String>) element;
         if (columnIndex == 0)
         {
            return entry.getKey();
         }
         else
         {
            return entry.getValue();
         }
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void addListener(ILabelProviderListener listener)
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
       *      java.lang.String)
       */
      public boolean isLabelProperty(Object element, String property)
      {
         return false;
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void removeListener(ILabelProviderListener listener)
      {
      }

   }


   class BoundObjectsTableContentProvider implements IStructuredContentProvider
   {

      /**
       * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
       */
      public Object[] getElements(Object inputElement)
      {
         Trace trace = (Trace) inputElement;
         return trace.entrySetOfBindings().toArray();
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
       *      java.lang.Object, java.lang.Object)
       */
      public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
      {
      }

   }


   class BoundObjectsTableLabelProvider implements ITableLabelProvider
   {

      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
       */
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }


      /**
       * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
       */
      public String getColumnText(Object element, int columnIndex)
      {
         Entry<String, TGObject> entry = (Entry<String, TGObject>) element;
         if (columnIndex == 0)
         {
            return entry.getKey();
         }
         else
         {
            return entry.getValue().getId();
         }
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void addListener(ILabelProviderListener listener)
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
       */
      public void dispose()
      {
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
       *      java.lang.String)
       */
      public boolean isLabelProperty(Object element, String property)
      {
         return false;
      }


      /**
       * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
       */
      public void removeListener(ILabelProviderListener listener)
      {
      }

   }

}