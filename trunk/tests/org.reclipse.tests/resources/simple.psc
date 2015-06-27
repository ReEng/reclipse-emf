<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_mJDAsK9REeCKeeTFCHcyGg" name="Simple Test Catalog" metamodel="de.fzi.gast">
  <patternSpecifications id="_mJDAsa9REeCKeeTFCHcyGg" name="SingleObject">
    <connections xsi:type="specification:PSLink" id="_qi0dUK9REeCKeeTFCHcyGg" name="annotatedElement"
        source="#_mJDAsq9REeCKeeTFCHcyGg" target="#_o9EAcK9REeCKeeTFCHcyGg" qualifier="object"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_mJDAsq9REeCKeeTFCHcyGg" outgoing="#_qi0dUK9REeCKeeTFCHcyGg"
        type="#_mJDAsa9REeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_o9EAcK9REeCKeeTFCHcyGg" name="obj1"
        incoming="#_qi0dUK9REeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/core#//Package"/>
  </patternSpecifications>
  <patternSpecifications id="_vHm3IK9REeCKeeTFCHcyGg" name="SingleObjectAttributeConstraints">
    <connections xsi:type="specification:PSLink" id="_zVyHwK9REeCKeeTFCHcyGg" name="annotatedElement"
        source="#_vHm3Ia9REeCKeeTFCHcyGg" target="#_v0FioK9REeCKeeTFCHcyGg" qualifier="object"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_vHm3Ia9REeCKeeTFCHcyGg" outgoing="#_zVyHwK9REeCKeeTFCHcyGg"
        type="#_vHm3IK9REeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_v0FioK9REeCKeeTFCHcyGg" name="obj1"
        incoming="#_zVyHwK9REeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_w8sJcK9REeCKeeTFCHcyGg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_1ICIgK9REeCKeeTFCHcyGg"
          valueExpression="Object" operator="UNEQUAL" attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_3qpPMK9REeCKeeTFCHcyGg"
          valueExpression="VISIBILITYPUBLIC" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_6qazAK9REeCKeeTFCHcyGg"
          valueExpression="0" operator="GREATER_OR_EQUAL" attribute="http://www.fzi.de/gast/types#//GASTClass/linesOfComments"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_KNQ10K9SEeCKeeTFCHcyGg" name="NegativeObject">
    <connections xsi:type="specification:PSLink" id="_Nn9bEK9SEeCKeeTFCHcyGg" name="link1"
        source="#_K1IuIK9SEeCKeeTFCHcyGg" target="#_MhvZ4K9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <connections xsi:type="specification:PSLink" id="_N4ntQK9SEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_KNQ10a9SEeCKeeTFCHcyGg" target="#_K1IuIK9SEeCKeeTFCHcyGg" qualifier="object"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_KNQ10a9SEeCKeeTFCHcyGg" outgoing="#_N4ntQK9SEeCKeeTFCHcyGg"
        type="#_KNQ10K9SEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_K1IuIK9SEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_Nn9bEK9SEeCKeeTFCHcyGg" incoming="#_N4ntQK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_MhvZ4K9SEeCKeeTFCHcyGg" name="obj2"
        modifier="NEGATIVE" incoming="#_Nn9bEK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_TH9f8K9SEeCKeeTFCHcyGg" name="SetObject">
    <connections xsi:type="specification:PSLink" id="_UT-nIK9SEeCKeeTFCHcyGg" name="link1"
        source="#_TyEM4K9SEeCKeeTFCHcyGg" target="#_UAo0YK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_UdWlkK9SEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_TH9f8a9SEeCKeeTFCHcyGg" target="#_TyEM4K9SEeCKeeTFCHcyGg" qualifier="object"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_VvVK0K9SEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_TH9f8a9SEeCKeeTFCHcyGg" target="#_UAo0YK9SEeCKeeTFCHcyGg" qualifier="methods"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_TH9f8a9SEeCKeeTFCHcyGg" outgoing="#_UdWlkK9SEeCKeeTFCHcyGg #_VvVK0K9SEeCKeeTFCHcyGg"
        type="#_TH9f8K9SEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_TyEM4K9SEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_UT-nIK9SEeCKeeTFCHcyGg" incoming="#_UdWlkK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_UAo0YK9SEeCKeeTFCHcyGg" name="obj2"
        modifier="SET" incoming="#_UT-nIK9SEeCKeeTFCHcyGg #_VvVK0K9SEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
  </patternSpecifications>
  <patternSpecifications id="_cZt_MK9SEeCKeeTFCHcyGg" name="AdditionalObject">
    <connections xsi:type="specification:PSLink" id="_dtU8IK9SEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_cZt_Ma9SEeCKeeTFCHcyGg" target="#_dHPMgK9SEeCKeeTFCHcyGg" qualifier="object"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_d3chcK9SEeCKeeTFCHcyGg" name="link2"
        source="#_dHPMgK9SEeCKeeTFCHcyGg" target="#_dWHWAK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <nodes xsi:type="specification:PSAnnotation" id="_cZt_Ma9SEeCKeeTFCHcyGg" outgoing="#_dtU8IK9SEeCKeeTFCHcyGg"
        type="#_cZt_MK9SEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_dHPMgK9SEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_d3chcK9SEeCKeeTFCHcyGg" incoming="#_dtU8IK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_dWHWAK9SEeCKeeTFCHcyGg" name="obj2"
        modifier="ADDITIONAL" incoming="#_d3chcK9SEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
</specification:PSCatalog>
