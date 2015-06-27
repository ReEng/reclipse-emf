<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_ovwPsK1NEeCtn6bi_IANnw"
    name="Patterns Catalog" metamodel="de.fzi.gast">
  <patternSpecifications id="_ovwPsa1NEeCtn6bi_IANnw" name="Singleton">
    <connections xsi:type="specification:PSLink" id="_FVfhEK1OEeCtn6bi_IANnw" name="annotatedElement"
        source="#_ovwPsq1NEeCtn6bi_IANnw" target="#_s61JAK1NEeCtn6bi_IANnw" qualifier="instance"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_F2c5EK1OEeCtn6bi_IANnw" name="annotatedElement"
        source="#_ovwPsq1NEeCtn6bi_IANnw" target="#_uVUKAK1NEeCtn6bi_IANnw" qualifier="singleton"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_GXzSoK1OEeCtn6bi_IANnw" name="link3"
        source="#_s61JAK1NEeCtn6bi_IANnw" target="#_uVUKAK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_G-7kEK1OEeCtn6bi_IANnw" name="link4"
        source="#_uVUKAK1NEeCtn6bi_IANnw" target="#_upm_AK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/types#//GASTClass/constructors"/>
    <connections xsi:type="specification:PSLink" id="_H-j1EK1OEeCtn6bi_IANnw" name="link6"
        source="#_uVUKAK1NEeCtn6bi_IANnw" target="#_wZ1mgK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_IjBNAK1OEeCtn6bi_IANnw" name="link7"
        source="#_wZ1mgK1NEeCtn6bi_IANnw" target="#_vysuAK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_IvdGgK1OEeCtn6bi_IANnw" name="link8"
        source="#_vysuAK1NEeCtn6bi_IANnw" target="#_uVUKAK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_J3uWIK1OEeCtn6bi_IANnw" name="link9"
        source="#_wZ1mgK1NEeCtn6bi_IANnw" target="#_JQy5AK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/functions#//Function/accesses"/>
    <connections xsi:type="specification:PSLink" id="_KHqPkK1OEeCtn6bi_IANnw" name="link10"
        source="#_JQy5AK1OEeCtn6bi_IANnw" target="#_s61JAK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_HRsvEK1OEeCtn6bi_IANnw" name="link5"
        source="#_uVUKAK1NEeCtn6bi_IANnw" target="#_vCyUAK1NEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/types#//GASTClass/constructors"/>
    <connections xsi:type="specification:PSLink" id="_CX1rQLxDEeCmFO4k1VVXaw" name="link11"
        source="#_9lDRsLxCEeCmFO4k1VVXaw" target="#_wZ1mgK1NEeCtn6bi_IANnw" qualifier="getMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_ovwPsq1NEeCtn6bi_IANnw" outgoing="#_FVfhEK1OEeCtn6bi_IANnw #_F2c5EK1OEeCtn6bi_IANnw"
        type="#_ovwPsa1NEeCtn6bi_IANnw"/>
    <nodes xsi:type="specification:PSObject" id="_s61JAK1NEeCtn6bi_IANnw" name="instanceField"
        outgoing="#_GXzSoK1OEeCtn6bi_IANnw" incoming="#_FVfhEK1OEeCtn6bi_IANnw #_KHqPkK1OEeCtn6bi_IANnw"
        instanceOf="http://www.fzi.de/gast/variables#//Field">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_eULzQK1OEeCtn6bi_IANnw"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/static"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_hg4_0K1OEeCtn6bi_IANnw"
          valueExpression="VISIBILITYPRIVAT" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_uVUKAK1NEeCtn6bi_IANnw" name="singletonClass"
        outgoing="#_G-7kEK1OEeCtn6bi_IANnw #_H-j1EK1OEeCtn6bi_IANnw #_HRsvEK1OEeCtn6bi_IANnw"
        incoming="#_F2c5EK1OEeCtn6bi_IANnw #_GXzSoK1OEeCtn6bi_IANnw #_IvdGgK1OEeCtn6bi_IANnw"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_upm_AK1NEeCtn6bi_IANnw" name="privateConstructor"
        incoming="#_G-7kEK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/functions#//Constructor">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_lI5oUK1OEeCtn6bi_IANnw"
          valueExpression="VISIBILITYPRIVAT" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_vysuAK1NEeCtn6bi_IANnw" name="typeAccess"
        outgoing="#_IvdGgK1OEeCtn6bi_IANnw" incoming="#_IjBNAK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_wZ1mgK1NEeCtn6bi_IANnw" name="accessorMethod"
        outgoing="#_IjBNAK1OEeCtn6bi_IANnw #_J3uWIK1OEeCtn6bi_IANnw" incoming="#_H-j1EK1OEeCtn6bi_IANnw #_CX1rQLxDEeCmFO4k1VVXaw"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_DPbakLxDEeCmFO4k1VVXaw"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/static"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_JQy5AK1OEeCtn6bi_IANnw" name="fieldAccess"
        outgoing="#_KHqPkK1OEeCtn6bi_IANnw" incoming="#_J3uWIK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess"/>
    <nodes xsi:type="specification:PSObject" id="_vCyUAK1NEeCtn6bi_IANnw" name="publicConstructor"
        modifier="NEGATIVE" incoming="#_HRsvEK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/functions#//Constructor">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_1q7I0K1REeCtn6bi_IANnw"
          valueExpression="VISIBILITYPUBLIC" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_9lDRsLxCEeCmFO4k1VVXaw" name="getMethod"
        outgoing="#_CX1rQLxDEeCmFO4k1VVXaw" type="#_ixwVsLw_EeCmFO4k1VVXaw"/>
  </patternSpecifications>
  <patternSpecifications id="_Su1I4K8_EeCF_8JYN4-2IA" name="Generalization" subPatterns="#_dlelwLEVEeCUkfYLwzc8Zg #_cgt9ILEVEeCUkfYLwzc8Zg"
      abstract="true">
    <connections xsi:type="specification:PSLink" id="_ZAU8IK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Su1v8K8_EeCF_8JYN4-2IA" target="#_TsCYAK8_EeCF_8JYN4-2IA" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_ZKjPIK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Su1v8K8_EeCF_8JYN4-2IA" target="#_XQsKgK8_EeCF_8JYN4-2IA" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Su1v8K8_EeCF_8JYN4-2IA" outgoing="#_ZAU8IK8_EeCF_8JYN4-2IA #_ZKjPIK8_EeCF_8JYN4-2IA"
        type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_TsCYAK8_EeCF_8JYN4-2IA" name="superType"
        incoming="#_ZAU8IK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_XQsKgK8_EeCF_8JYN4-2IA" name="subType"
        incoming="#_ZKjPIK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_gKPlIK8_EeCF_8JYN4-2IA" name="Observer">
    <connections xsi:type="specification:PSLink" id="_ozEcIK8_EeCF_8JYN4-2IA" name="link1"
        source="#_mAYSEK8_EeCF_8JYN4-2IA" target="#_nPo6gK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_pPwYIK8_EeCF_8JYN4-2IA" name="link2"
        source="#_kNFvgK8_EeCF_8JYN4-2IA" target="#_mAYSEK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_ppUHoK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_kNFvgK8_EeCF_8JYN4-2IA" qualifier="subjectClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_p_wRsK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_haHRkK8_EeCF_8JYN4-2IA" qualifier="observerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_qXFzoK8_EeCF_8JYN4-2IA" name="link5"
        source="#_nPo6gK8_EeCF_8JYN4-2IA" target="#_haHRkK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_w864IK8_EeCF_8JYN4-2IA" name="link6"
        source="#_kNFvgK8_EeCF_8JYN4-2IA" target="#_vpwAEK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_xM3YoK8_EeCF_8JYN4-2IA" name="link7"
        source="#_haHRkK8_EeCF_8JYN4-2IA" target="#_udExkK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_j9ka4K9BEeCF_8JYN4-2IA" name="link8"
        source="#_zOGQkK8_EeCF_8JYN4-2IA" target="#_udExkK8_EeCF_8JYN4-2IA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_kN-OYK9BEeCF_8JYN4-2IA" name="link9"
        source="#_zOGQkK8_EeCF_8JYN4-2IA" target="#_vpwAEK8_EeCF_8JYN4-2IA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Efx9kLEeEeCUkfYLwzc8Zg" name="link10"
        source="#_C7_PkLEeEeCUkfYLwzc8Zg" target="#_kNFvgK8_EeCF_8JYN4-2IA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_E1hkELEeEeCUkfYLwzc8Zg" name="link11"
        source="#_C7_PkLEeEeCUkfYLwzc8Zg" target="#_haHRkK8_EeCF_8JYN4-2IA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_MXeSkLEeEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_udExkK8_EeCF_8JYN4-2IA" qualifier="updateMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_MmMrELEeEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_mAYSEK8_EeCF_8JYN4-2IA" qualifier="registerMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_M01kALEeEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_vpwAEK8_EeCF_8JYN4-2IA" qualifier="notifyMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_YFc8oLEeEeCUkfYLwzc8Zg" name="link15"
        source="#_W3PdELEeEeCUkfYLwzc8Zg" target="#_udExkK8_EeCF_8JYN4-2IA" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_gKPlIa8_EeCF_8JYN4-2IA" outgoing="#_ppUHoK8_EeCF_8JYN4-2IA #_p_wRsK8_EeCF_8JYN4-2IA #_MXeSkLEeEeCUkfYLwzc8Zg #_MmMrELEeEeCUkfYLwzc8Zg #_M01kALEeEeCUkfYLwzc8Zg"
        type="#_gKPlIK8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_haHRkK8_EeCF_8JYN4-2IA" name="observerClass"
        outgoing="#_xM3YoK8_EeCF_8JYN4-2IA" incoming="#_p_wRsK8_EeCF_8JYN4-2IA #_qXFzoK8_EeCF_8JYN4-2IA #_E1hkELEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_1HvSwK8_EeCF_8JYN4-2IA"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_3pVs0K8_EeCF_8JYN4-2IA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//GASTClass/interface"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_kNFvgK8_EeCF_8JYN4-2IA" name="subjectClass"
        outgoing="#_pPwYIK8_EeCF_8JYN4-2IA #_w864IK8_EeCF_8JYN4-2IA" incoming="#_ppUHoK8_EeCF_8JYN4-2IA #_Efx9kLEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_7-DqUK8_EeCF_8JYN4-2IA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_mAYSEK8_EeCF_8JYN4-2IA" name="registerMethod"
        outgoing="#_ozEcIK8_EeCF_8JYN4-2IA" incoming="#_pPwYIK8_EeCF_8JYN4-2IA #_MmMrELEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_nPo6gK8_EeCF_8JYN4-2IA" name="param"
        outgoing="#_qXFzoK8_EeCF_8JYN4-2IA" incoming="#_ozEcIK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_udExkK8_EeCF_8JYN4-2IA" name="updateMethod"
        incoming="#_xM3YoK8_EeCF_8JYN4-2IA #_j9ka4K9BEeCF_8JYN4-2IA #_MXeSkLEeEeCUkfYLwzc8Zg #_YFc8oLEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_vpwAEK8_EeCF_8JYN4-2IA" name="notifyMethod"
        incoming="#_w864IK8_EeCF_8JYN4-2IA #_kN-OYK9BEeCF_8JYN4-2IA #_M01kALEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_zOGQkK8_EeCF_8JYN4-2IA" name="del"
        outgoing="#_j9ka4K9BEeCF_8JYN4-2IA #_kN-OYK9BEeCF_8JYN4-2IA" type="#_zz1goK0rEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_C7_PkLEeEeCUkfYLwzc8Zg" name="ref"
        outgoing="#_Efx9kLEeEeCUkfYLwzc8Zg #_E1hkELEeEeCUkfYLwzc8Zg" type="#_emQtULEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSAnnotation" id="_W3PdELEeEeCUkfYLwzc8Zg" name="concreteObservers"
        parents="#_0u7oELEqEeCUkfYLwzc8Zg" outgoing="#_YFc8oLEeEeCUkfYLwzc8Zg" type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <combinedFragments id="_0u7oELEqEeCUkfYLwzc8Zg" name="frag1" children="#_W3PdELEeEeCUkfYLwzc8Zg"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_CBRJ8K9AEeCF_8JYN4-2IA" name="Delegation" subPatterns="#_zz1goK0rEeC73qeN65GIrA #_3qb0MK0qEeC73qeN65GIrA"
      abstract="true">
    <connections xsi:type="specification:PSLink" id="_G179YK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_BzRUwK9BEeCF_8JYN4-2IA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HBXxUK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_CDStwK9BEeCF_8JYN4-2IA" qualifier="callerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HtAvUK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_Ca7xsK9BEeCF_8JYN4-2IA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_CBRJ8a9AEeCF_8JYN4-2IA" outgoing="#_G179YK9BEeCF_8JYN4-2IA #_HBXxUK9BEeCF_8JYN4-2IA #_HtAvUK9BEeCF_8JYN4-2IA"
        type="#_CBRJ8K9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_BzRUwK9BEeCF_8JYN4-2IA" name="caller"
        incoming="#_G179YK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_CDStwK9BEeCF_8JYN4-2IA" name="callerClass"
        incoming="#_HBXxUK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_Ca7xsK9BEeCF_8JYN4-2IA" name="callee"
        incoming="#_HtAvUK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
  </patternSpecifications>
  <patternSpecifications id="_Xl_PoK9AEeCF_8JYN4-2IA" name="Reference" subPatterns="#_nkALgK9AEeCF_8JYN4-2IA #_emQtULEVEeCUkfYLwzc8Zg"
      abstract="true">
    <connections xsi:type="specification:PSLink" id="_bC9FwK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Xl_Poa9AEeCF_8JYN4-2IA" target="#_ZSLrsK9AEeCF_8JYN4-2IA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_bPnowK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Xl_Poa9AEeCF_8JYN4-2IA" target="#_ZbG-MK9AEeCF_8JYN4-2IA" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_bfVfwK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Xl_Poa9AEeCF_8JYN4-2IA" target="#_ZnZGsK9AEeCF_8JYN4-2IA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Xl_Poa9AEeCF_8JYN4-2IA" outgoing="#_bC9FwK9AEeCF_8JYN4-2IA #_bPnowK9AEeCF_8JYN4-2IA #_bfVfwK9AEeCF_8JYN4-2IA"
        type="#_Xl_PoK9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_ZSLrsK9AEeCF_8JYN4-2IA" name="referencingClass"
        incoming="#_bC9FwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_ZbG-MK9AEeCF_8JYN4-2IA" name="field"
        incoming="#_bPnowK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_ZnZGsK9AEeCF_8JYN4-2IA" name="referencedClass"
        incoming="#_bfVfwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_nkALgK9AEeCF_8JYN4-2IA" name="SingleReference" superPattern="#_Xl_PoK9AEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_xvOCUK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_nkALga9AEeCF_8JYN4-2IA" target="#_uCxlwK9AEeCF_8JYN4-2IA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_x9kAUK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_nkALga9AEeCF_8JYN4-2IA" target="#_uStfMK9AEeCF_8JYN4-2IA" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_y2PUwK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_nkALga9AEeCF_8JYN4-2IA" target="#_uePZwK9AEeCF_8JYN4-2IA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_1QvoUK9AEeCF_8JYN4-2IA" name="link4"
        source="#_uCxlwK9AEeCF_8JYN4-2IA" target="#_uStfMK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_1bpQwK9AEeCF_8JYN4-2IA" name="link5"
        source="#_uStfMK9AEeCF_8JYN4-2IA" target="#_uePZwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_QY9pELGBEeCDAa1tErEpvg" name="link6"
        source="#_NTP1ULGBEeCDAa1tErEpvg" target="#_uStfMK9AEeCF_8JYN4-2IA" qualifier="setField"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_QqXiILGBEeCDAa1tErEpvg" name="link7"
        source="#_M7ljQLGBEeCDAa1tErEpvg" target="#_uStfMK9AEeCF_8JYN4-2IA" qualifier="returnedField"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_nkALga9AEeCF_8JYN4-2IA" outgoing="#_xvOCUK9AEeCF_8JYN4-2IA #_x9kAUK9AEeCF_8JYN4-2IA #_y2PUwK9AEeCF_8JYN4-2IA"
        type="#_nkALgK9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_uCxlwK9AEeCF_8JYN4-2IA" name="referencingClass"
        outgoing="#_1QvoUK9AEeCF_8JYN4-2IA" incoming="#_xvOCUK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_uStfMK9AEeCF_8JYN4-2IA" name="field"
        outgoing="#_1bpQwK9AEeCF_8JYN4-2IA" incoming="#_x9kAUK9AEeCF_8JYN4-2IA #_1QvoUK9AEeCF_8JYN4-2IA #_QY9pELGBEeCDAa1tErEpvg #_QqXiILGBEeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_uePZwK9AEeCF_8JYN4-2IA" name="references"
        incoming="#_y2PUwK9AEeCF_8JYN4-2IA #_1bpQwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_M7ljQLGBEeCDAa1tErEpvg" name="getter"
        modifier="ADDITIONAL" outgoing="#_QqXiILGBEeCDAa1tErEpvg" type="#_neldoLF9EeCDAa1tErEpvg"/>
    <nodes xsi:type="specification:PSAnnotation" id="_NTP1ULGBEeCDAa1tErEpvg" name="setter"
        modifier="ADDITIONAL" outgoing="#_QY9pELGBEeCDAa1tErEpvg" type="#_bUtzsLF7EeCDAa1tErEpvg"/>
  </patternSpecifications>
  <patternSpecifications id="_vXaoAK9BEeCF_8JYN4-2IA" name="OverridingMethod">
    <connections xsi:type="specification:PSLink" id="_yspecK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_vXaoAa9BEeCF_8JYN4-2IA" target="#_wz2w0K9BEeCF_8JYN4-2IA" qualifier="overriding"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_y_fh8K9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_vXaoAa9BEeCF_8JYN4-2IA" target="#_wmC-UK9BEeCF_8JYN4-2IA" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_-QUV8K9BEeCF_8JYN4-2IA" name="link7"
        source="#_wmC-UK9BEeCF_8JYN4-2IA" target="#_82DXYK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method/surroundingClass"/>
    <connections xsi:type="specification:PSLink" id="_AWcU8K9CEeCF_8JYN4-2IA" name="link8"
        source="#_wz2w0K9BEeCF_8JYN4-2IA" target="#_9HEO4K9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method/surroundingClass"/>
    <connections xsi:type="specification:PSLink" id="_ClMrgK9CEeCF_8JYN4-2IA" name="link9"
        source="#_76oQ8K9BEeCF_8JYN4-2IA" target="#_82DXYK9BEeCF_8JYN4-2IA" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_C4MgAK9CEeCF_8JYN4-2IA" name="link10"
        source="#_76oQ8K9BEeCF_8JYN4-2IA" target="#_9HEO4K9BEeCF_8JYN4-2IA" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HcvXkLEWEeCUkfYLwzc8Zg" name="link8"
        source="#_wz2w0K9BEeCF_8JYN4-2IA" target="#_wmC-UK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//Member/overriddenMember"/>
    <nodes xsi:type="specification:PSAnnotation" id="_vXaoAa9BEeCF_8JYN4-2IA" outgoing="#_yspecK9BEeCF_8JYN4-2IA #_y_fh8K9BEeCF_8JYN4-2IA"
        type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_wmC-UK9BEeCF_8JYN4-2IA" name="overridden"
        outgoing="#_-QUV8K9BEeCF_8JYN4-2IA" incoming="#_y_fh8K9BEeCF_8JYN4-2IA #_HcvXkLEWEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_wz2w0K9BEeCF_8JYN4-2IA" name="overriding"
        outgoing="#_AWcU8K9CEeCF_8JYN4-2IA #_HcvXkLEWEeCUkfYLwzc8Zg" incoming="#_yspecK9BEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_76oQ8K9BEeCF_8JYN4-2IA" name="gen"
        outgoing="#_ClMrgK9CEeCF_8JYN4-2IA #_C4MgAK9CEeCF_8JYN4-2IA" type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_82DXYK9BEeCF_8JYN4-2IA" name="superClass"
        incoming="#_-QUV8K9BEeCF_8JYN4-2IA #_ClMrgK9CEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_9HEO4K9BEeCF_8JYN4-2IA" name="subClass"
        incoming="#_AWcU8K9CEeCF_8JYN4-2IA #_C4MgAK9CEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_qxJ8oK9DEeCF_8JYN4-2IA" name="Strategy">
    <connections xsi:type="specification:PSLink" id="_wf1WsK9DEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_qxJ8oa9DEeCF_8JYN4-2IA" target="#_t8U4IK9DEeCF_8JYN4-2IA" qualifier="context"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_wvPrsK9DEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_qxJ8oa9DEeCF_8JYN4-2IA" target="#_sjI3oK9DEeCF_8JYN4-2IA" qualifier="strategy"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_31vNQK9DEeCF_8JYN4-2IA" name="link6"
        source="#_zu5TsK9DEeCF_8JYN4-2IA" target="#_sjI3oK9DEeCF_8JYN4-2IA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_9W5vwK9DEeCF_8JYN4-2IA" name="link7"
        source="#_sjI3oK9DEeCF_8JYN4-2IA" target="#_6Sb5MK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_928hwK9DEeCF_8JYN4-2IA" name="link8"
        source="#_t8U4IK9DEeCF_8JYN4-2IA" target="#_7khzMK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="__R9uQK9DEeCF_8JYN4-2IA" name="link9"
        source="#_-VTRsK9DEeCF_8JYN4-2IA" target="#_6Sb5MK9DEeCF_8JYN4-2IA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="__dQYQK9DEeCF_8JYN4-2IA" name="link10"
        source="#_-VTRsK9DEeCF_8JYN4-2IA" target="#_7khzMK9DEeCF_8JYN4-2IA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_jNY0ALEWEeCUkfYLwzc8Zg" name="link9"
        source="#_zu5TsK9DEeCF_8JYN4-2IA" target="#_t8U4IK9DEeCF_8JYN4-2IA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_xyWYoLEeEeCUkfYLwzc8Zg" name="link9"
        source="#_t8U4IK9DEeCF_8JYN4-2IA" target="#_p6MJkLEeEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_x9obkLEeEeCUkfYLwzc8Zg" name="link10"
        source="#_p6MJkLEeEeCUkfYLwzc8Zg" target="#_vmLcALEeEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_yRZFELEeEeCUkfYLwzc8Zg" name="link11"
        source="#_vmLcALEeEeCUkfYLwzc8Zg" target="#_sjI3oK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_AhtwELEfEeCUkfYLwzc8Zg" name="link12"
        source="#_6Sb5MK9DEeCF_8JYN4-2IA" target="#_-MjPgLEeEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_AudLkLEfEeCUkfYLwzc8Zg" name="link13"
        source="#_-MjPgLEeEeCUkfYLwzc8Zg" target="#_t8U4IK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_X-CQkLEfEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_qxJ8oa9DEeCF_8JYN4-2IA" target="#_7khzMK9DEeCF_8JYN4-2IA" qualifier="requestMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_YRfYELEfEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_qxJ8oa9DEeCF_8JYN4-2IA" target="#_6Sb5MK9DEeCF_8JYN4-2IA" qualifier="algorithmMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_g0X_ILEfEeCUkfYLwzc8Zg" name="link16"
        source="#_eWqmkLEfEeCUkfYLwzc8Zg" target="#_6Sb5MK9DEeCF_8JYN4-2IA" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_9D6f8LGAEeCDAa1tErEpvg" name="link17"
        source="#_7iBPYLGAEeCDAa1tErEpvg" target="#_p6MJkLEeEeCUkfYLwzc8Zg" qualifier="getMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_qxJ8oa9DEeCF_8JYN4-2IA" outgoing="#_wf1WsK9DEeCF_8JYN4-2IA #_wvPrsK9DEeCF_8JYN4-2IA #_X-CQkLEfEeCUkfYLwzc8Zg #_YRfYELEfEeCUkfYLwzc8Zg"
        type="#_qxJ8oK9DEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_sjI3oK9DEeCF_8JYN4-2IA" name="strategyClass"
        outgoing="#_9W5vwK9DEeCF_8JYN4-2IA" incoming="#_wvPrsK9DEeCF_8JYN4-2IA #_31vNQK9DEeCF_8JYN4-2IA #_yRZFELEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_F2VEULEfEeCUkfYLwzc8Zg"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_ISSJYLEfEeCUkfYLwzc8Zg"
          additional="true" valueExpression=".*(S|s)trategy.*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_t8U4IK9DEeCF_8JYN4-2IA" name="contextClass"
        outgoing="#_928hwK9DEeCF_8JYN4-2IA #_xyWYoLEeEeCUkfYLwzc8Zg" incoming="#_wf1WsK9DEeCF_8JYN4-2IA #_jNY0ALEWEeCUkfYLwzc8Zg #_AudLkLEfEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_zu5TsK9DEeCF_8JYN4-2IA" name="ref"
        outgoing="#_31vNQK9DEeCF_8JYN4-2IA #_jNY0ALEWEeCUkfYLwzc8Zg" type="#_emQtULEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_6Sb5MK9DEeCF_8JYN4-2IA" name="algorithm"
        outgoing="#_AhtwELEfEeCUkfYLwzc8Zg" incoming="#_9W5vwK9DEeCF_8JYN4-2IA #__R9uQK9DEeCF_8JYN4-2IA #_YRfYELEfEeCUkfYLwzc8Zg #_g0X_ILEfEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_Dnz-ULEfEeCUkfYLwzc8Zg"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_7khzMK9DEeCF_8JYN4-2IA" name="request"
        incoming="#_928hwK9DEeCF_8JYN4-2IA #__dQYQK9DEeCF_8JYN4-2IA #_X-CQkLEfEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_-VTRsK9DEeCF_8JYN4-2IA" name="del"
        outgoing="#__R9uQK9DEeCF_8JYN4-2IA #__dQYQK9DEeCF_8JYN4-2IA" type="#_CBRJ8K9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_p6MJkLEeEeCUkfYLwzc8Zg" name="setStrategy"
        outgoing="#_x9obkLEeEeCUkfYLwzc8Zg" incoming="#_xyWYoLEeEeCUkfYLwzc8Zg #_9D6f8LGAEeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_sTIW4LEeEeCUkfYLwzc8Zg"
          additional="true" valueExpression="set.*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_vmLcALEeEeCUkfYLwzc8Zg" name="param"
        modifier="ADDITIONAL" outgoing="#_yRZFELEeEeCUkfYLwzc8Zg" incoming="#_x9obkLEeEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_-MjPgLEeEeCUkfYLwzc8Zg" name="contextParam"
        modifier="ADDITIONAL" outgoing="#_AudLkLEfEeCUkfYLwzc8Zg" incoming="#_AhtwELEfEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSAnnotation" id="_eWqmkLEfEeCUkfYLwzc8Zg" name="concreteStrategies"
        parents="#_xFjGkLEqEeCUkfYLwzc8Zg" outgoing="#_g0X_ILEfEeCUkfYLwzc8Zg" type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_7iBPYLGAEeCDAa1tErEpvg" name="setter"
        modifier="ADDITIONAL" outgoing="#_9D6f8LGAEeCDAa1tErEpvg" type="#_bUtzsLF7EeCDAa1tErEpvg"/>
    <combinedFragments id="_xFjGkLEqEeCUkfYLwzc8Zg" name="frag1" children="#_eWqmkLEfEeCUkfYLwzc8Zg"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_cgt9ILEVEeCUkfYLwzc8Zg" name="DirectGeneralization"
      superPattern="#_Su1I4K8_EeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_tAwykLEWEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_cgukMLEVEeCUkfYLwzc8Zg" target="#_p_k5gLEWEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_tNgOELEWEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_cgukMLEVEeCUkfYLwzc8Zg" target="#_qSkuALEWEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_tiF-ALEWEeCUkfYLwzc8Zg" name="link3"
        source="#_qSkuALEWEeCUkfYLwzc8Zg" target="#_p_k5gLEWEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <nodes xsi:type="specification:PSAnnotation" id="_cgukMLEVEeCUkfYLwzc8Zg" outgoing="#_tAwykLEWEeCUkfYLwzc8Zg #_tNgOELEWEeCUkfYLwzc8Zg"
        type="#_cgt9ILEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_p_k5gLEWEeCUkfYLwzc8Zg" name="superType"
        incoming="#_tAwykLEWEeCUkfYLwzc8Zg #_tiF-ALEWEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_MDsuQNX6EeC4_4Y9pe3SQw"
          valueExpression="Object" operator="UNEQUAL" attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_qSkuALEWEeCUkfYLwzc8Zg" name="subType"
        outgoing="#_tiF-ALEWEeCUkfYLwzc8Zg" incoming="#_tNgOELEWEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_dlelwLEVEeCUkfYLwzc8Zg" name="IndirectGeneralization"
      superPattern="#_Su1I4K8_EeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_4pFgkLEWEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_dlelwbEVEeCUkfYLwzc8Zg" target="#_z_6WALEWEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_43z5ELEWEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_dlelwbEVEeCUkfYLwzc8Zg" target="#_0U0PALEWEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_VxjqkLEiEeCUkfYLwzc8Zg" name="link3"
        source="#_UQDbkLEiEeCUkfYLwzc8Zg" target="#_Xvv2kLEXEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_V9TnkLEiEeCUkfYLwzc8Zg" name="link4"
        source="#_UQDbkLEiEeCUkfYLwzc8Zg" target="#_0U0PALEWEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_ZkVYoLEiEeCUkfYLwzc8Zg" name="link5"
        source="#_XxCPALEiEeCUkfYLwzc8Zg" target="#_z_6WALEWEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Z3oIELEiEeCUkfYLwzc8Zg" name="link6"
        source="#_XxCPALEiEeCUkfYLwzc8Zg" target="#_Xvv2kLEXEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_dlelwbEVEeCUkfYLwzc8Zg" outgoing="#_4pFgkLEWEeCUkfYLwzc8Zg #_43z5ELEWEeCUkfYLwzc8Zg"
        type="#_dlelwLEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_z_6WALEWEeCUkfYLwzc8Zg" name="superClass"
        incoming="#_4pFgkLEWEeCUkfYLwzc8Zg #_ZkVYoLEiEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_SVFz0NX6EeC4_4Y9pe3SQw"
          valueExpression="Object" operator="UNEQUAL" attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_0U0PALEWEeCUkfYLwzc8Zg" name="subClass"
        incoming="#_43z5ELEWEeCUkfYLwzc8Zg #_V9TnkLEiEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_Xvv2kLEXEeCUkfYLwzc8Zg" name="intermediateClass"
        incoming="#_VxjqkLEiEeCUkfYLwzc8Zg #_Z3oIELEiEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_UQDbkLEiEeCUkfYLwzc8Zg" name="gen2"
        outgoing="#_VxjqkLEiEeCUkfYLwzc8Zg #_V9TnkLEiEeCUkfYLwzc8Zg" type="#_cgt9ILEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSAnnotation" id="_XxCPALEiEeCUkfYLwzc8Zg" name="gen1"
        outgoing="#_ZkVYoLEiEeCUkfYLwzc8Zg #_Z3oIELEiEeCUkfYLwzc8Zg" type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_emQtULEVEeCUkfYLwzc8Zg" name="MultiReference" superPattern="#_Xl_PoK9AEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_BWPWkLEaEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_emQtUbEVEeCUkfYLwzc8Zg" target="#_5bo3gLEZEeCUkfYLwzc8Zg" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_BpseELEaEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_emQtUbEVEeCUkfYLwzc8Zg" target="#_5xYeALEZEeCUkfYLwzc8Zg" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_CCuCELEaEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_emQtUbEVEeCUkfYLwzc8Zg" target="#_6C2ogLEZEeCUkfYLwzc8Zg" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_GedNkLEaEeCUkfYLwzc8Zg" name="link4"
        source="#_Emg0gLEaEeCUkfYLwzc8Zg" target="#_5bo3gLEZEeCUkfYLwzc8Zg" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_GuLEkLEaEeCUkfYLwzc8Zg" name="link5"
        source="#_Emg0gLEaEeCUkfYLwzc8Zg" target="#_5xYeALEZEeCUkfYLwzc8Zg" qualifier="owningClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_G-CskLEaEeCUkfYLwzc8Zg" name="link6"
        source="#_Emg0gLEaEeCUkfYLwzc8Zg" target="#_6C2ogLEZEeCUkfYLwzc8Zg" qualifier="containerContentType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_MGWL4LFWEeC57dSY1qJBVw" name="link8"
        source="#_5xYeALEZEeCUkfYLwzc8Zg" target="#_HNTZULFWEeC57dSY1qJBVw" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_MOur0LFWEeC57dSY1qJBVw" name="link9"
        source="#_HNTZULFWEeC57dSY1qJBVw" target="#_HcZlQLFWEeC57dSY1qJBVw" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_MXlF0LFWEeC57dSY1qJBVw" name="link10"
        source="#_HcZlQLFWEeC57dSY1qJBVw" target="#_6C2ogLEZEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_nv0pULFWEeC57dSY1qJBVw" name="link10"
        source="#_5xYeALEZEeCUkfYLwzc8Zg" target="#_5bo3gLEZEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_Kdve4LYCEeCuArslgmqeJw" name="link11"
        source="#_Emg0gLEaEeCUkfYLwzc8Zg" target="#_HNTZULFWEeC57dSY1qJBVw" qualifier="accessMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_emQtUbEVEeCUkfYLwzc8Zg" outgoing="#_BWPWkLEaEeCUkfYLwzc8Zg #_BpseELEaEeCUkfYLwzc8Zg #_CCuCELEaEeCUkfYLwzc8Zg"
        type="#_emQtULEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_5bo3gLEZEeCUkfYLwzc8Zg" name="field"
        incoming="#_BWPWkLEaEeCUkfYLwzc8Zg #_GedNkLEaEeCUkfYLwzc8Zg #_nv0pULFWEeC57dSY1qJBVw"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_5xYeALEZEeCUkfYLwzc8Zg" name="referencing"
        outgoing="#_MGWL4LFWEeC57dSY1qJBVw #_nv0pULFWEeC57dSY1qJBVw" incoming="#_BpseELEaEeCUkfYLwzc8Zg #_GuLEkLEaEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_6C2ogLEZEeCUkfYLwzc8Zg" name="referenced"
        incoming="#_CCuCELEaEeCUkfYLwzc8Zg #_G-CskLEaEeCUkfYLwzc8Zg #_MXlF0LFWEeC57dSY1qJBVw"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Emg0gLEaEeCUkfYLwzc8Zg" name="containerAccessMethod"
        modifier="SET" outgoing="#_GedNkLEaEeCUkfYLwzc8Zg #_GuLEkLEaEeCUkfYLwzc8Zg #_G-CskLEaEeCUkfYLwzc8Zg #_Kdve4LYCEeCuArslgmqeJw"
        type="#_hb758LEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_HNTZULFWEeC57dSY1qJBVw" name="accessMethod"
        outgoing="#_MOur0LFWEeC57dSY1qJBVw" incoming="#_MGWL4LFWEeC57dSY1qJBVw #_Kdve4LYCEeCuArslgmqeJw"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_HcZlQLFWEeC57dSY1qJBVw" name="accessMethodParam"
        outgoing="#_MXlF0LFWEeC57dSY1qJBVw" incoming="#_MOur0LFWEeC57dSY1qJBVw" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
  </patternSpecifications>
  <patternSpecifications id="_hb758LEVEeCUkfYLwzc8Zg" name="ContainerWriteAccessMethod">
    <connections xsi:type="specification:PSLink" id="_2sHBELEXEeCUkfYLwzc8Zg" name="link1"
        source="#_yYrdALEXEeCUkfYLwzc8Zg" target="#_z0JVcLEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_47Y8ELEXEeCUkfYLwzc8Zg" name="link2"
        source="#_30gMgLEXEeCUkfYLwzc8Zg" target="#_z0JVcLEXEeCUkfYLwzc8Zg" qualifier="containerType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_IJp2ALEYEeCUkfYLwzc8Zg" name="link3"
        source="#_6pZWALEXEeCUkfYLwzc8Zg" target="#_yYrdALEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_IexKYLEYEeCUkfYLwzc8Zg" name="link4"
        source="#_6pZWALEXEeCUkfYLwzc8Zg" target="#_8nIOALEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_ItwBkLEYEeCUkfYLwzc8Zg" name="link5"
        source="#_8nIOALEXEeCUkfYLwzc8Zg" target="#__HuigLEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_KXkfELEYEeCUkfYLwzc8Zg" name="link6"
        source="#_E3xOgLEYEeCUkfYLwzc8Zg" target="#_yYrdALEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSPath" id="_K3hxgLEYEeCUkfYLwzc8Zg" name="path1"
        source="#_8nIOALEXEeCUkfYLwzc8Zg" target="#_CXx-ALEYEeCUkfYLwzc8Zg"/>
    <connections xsi:type="specification:PSPath" id="_LKYcELEYEeCUkfYLwzc8Zg" name="path2"
        source="#_CXx-ALEYEeCUkfYLwzc8Zg" target="#_E3xOgLEYEeCUkfYLwzc8Zg"/>
    <connections xsi:type="specification:PSPath" id="_LWXCkLEYEeCUkfYLwzc8Zg" name="path3"
        source="#_CXx-ALEYEeCUkfYLwzc8Zg" target="#_FILCALEYEeCUkfYLwzc8Zg"/>
    <connections xsi:type="specification:PSPath" id="_Lj3TELEYEeCUkfYLwzc8Zg" name="path4"
        source="#_CXx-ALEYEeCUkfYLwzc8Zg" target="#_FbPvALEYEeCUkfYLwzc8Zg"/>
    <connections xsi:type="specification:PSLink" id="_MDEXkLEYEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_hb758bEVEeCUkfYLwzc8Zg" target="#_yYrdALEXEeCUkfYLwzc8Zg" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_MM1XkLEYEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_hb758bEVEeCUkfYLwzc8Zg" target="#_6pZWALEXEeCUkfYLwzc8Zg" qualifier="owningClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_MmFlELEYEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_hb758bEVEeCUkfYLwzc8Zg" target="#_8nIOALEXEeCUkfYLwzc8Zg" qualifier="accessMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_O5t0kLEYEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_hb758bEVEeCUkfYLwzc8Zg" target="#_M2exgLEYEeCUkfYLwzc8Zg" qualifier="containerContentType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_rjq3kLEYEeCUkfYLwzc8Zg" name="link11"
        source="#__HuigLEXEeCUkfYLwzc8Zg" target="#_M2exgLEYEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_uoM_kLEYEeCUkfYLwzc8Zg" name="link12"
        source="#_FILCALEYEeCUkfYLwzc8Zg" target="#__HuigLEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_FnTYkLEZEeCUkfYLwzc8Zg" name="link13"
        source="#_FbPvALEYEeCUkfYLwzc8Zg" target="#_z0JVcLEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_hb758bEVEeCUkfYLwzc8Zg" outgoing="#_MDEXkLEYEeCUkfYLwzc8Zg #_MM1XkLEYEeCUkfYLwzc8Zg #_MmFlELEYEeCUkfYLwzc8Zg #_O5t0kLEYEeCUkfYLwzc8Zg"
        type="#_hb758LEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_yYrdALEXEeCUkfYLwzc8Zg" name="field"
        outgoing="#_2sHBELEXEeCUkfYLwzc8Zg" incoming="#_IJp2ALEYEeCUkfYLwzc8Zg #_KXkfELEYEeCUkfYLwzc8Zg #_MDEXkLEYEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_z0JVcLEXEeCUkfYLwzc8Zg" name="containerType"
        incoming="#_2sHBELEXEeCUkfYLwzc8Zg #_47Y8ELEXEeCUkfYLwzc8Zg #_FnTYkLEZEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_30gMgLEXEeCUkfYLwzc8Zg" name="c"
        outgoing="#_47Y8ELEXEeCUkfYLwzc8Zg" type="#_etoOsLEXEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_6pZWALEXEeCUkfYLwzc8Zg" name="owningClass"
        outgoing="#_IJp2ALEYEeCUkfYLwzc8Zg #_IexKYLEYEeCUkfYLwzc8Zg" incoming="#_MM1XkLEYEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_8nIOALEXEeCUkfYLwzc8Zg" name="accessMethod"
        outgoing="#_ItwBkLEYEeCUkfYLwzc8Zg #_K3hxgLEYEeCUkfYLwzc8Zg" incoming="#_IexKYLEYEeCUkfYLwzc8Zg #_MmFlELEYEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_ipnGoLEYEeCUkfYLwzc8Zg"
          additional="true" valueExpression="(add|put|remove).*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_jJncYLEYEeCUkfYLwzc8Zg"
          valueExpression="VISIBILITYPRIVAT" operator="UNEQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_jwcy4LEYEeCUkfYLwzc8Zg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/static"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="__HuigLEXEeCUkfYLwzc8Zg" name="param"
        outgoing="#_rjq3kLEYEeCUkfYLwzc8Zg" incoming="#_ItwBkLEYEeCUkfYLwzc8Zg #_uoM_kLEYEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_CXx-ALEYEeCUkfYLwzc8Zg" name="accessStmt"
        outgoing="#_LKYcELEYEeCUkfYLwzc8Zg #_LWXCkLEYEeCUkfYLwzc8Zg #_Lj3TELEYEeCUkfYLwzc8Zg"
        incoming="#_K3hxgLEYEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/statements#//SimpleStatement"/>
    <nodes xsi:type="specification:PSObject" id="_E3xOgLEYEeCUkfYLwzc8Zg" name="fieldAccess"
        outgoing="#_KXkfELEYEeCUkfYLwzc8Zg" incoming="#_LKYcELEYEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess"/>
    <nodes xsi:type="specification:PSObject" id="_FILCALEYEeCUkfYLwzc8Zg" name="paramAccess"
        outgoing="#_uoM_kLEYEeCUkfYLwzc8Zg" incoming="#_LWXCkLEYEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess"/>
    <nodes xsi:type="specification:PSObject" id="_FbPvALEYEeCUkfYLwzc8Zg" name="functionAccess"
        outgoing="#_FnTYkLEZEeCUkfYLwzc8Zg" incoming="#_Lj3TELEYEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <nodes xsi:type="specification:PSObject" id="_M2exgLEYEeCUkfYLwzc8Zg" name="containerContentType"
        incoming="#_O5t0kLEYEeCUkfYLwzc8Zg #_rjq3kLEYEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_etoOsLEXEeCUkfYLwzc8Zg" name="ContainerType">
    <connections xsi:type="specification:PSLink" id="_s6f9ELEXEeCUkfYLwzc8Zg" name="link1"
        source="#_rSMigLEXEeCUkfYLwzc8Zg" target="#_g6XMALEXEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_tOQmkLEXEeCUkfYLwzc8Zg" name="link2"
        source="#_rSMigLEXEeCUkfYLwzc8Zg" target="#_fkTYALEXEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_vHuCkLEXEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_etoOsbEXEeCUkfYLwzc8Zg" target="#_fkTYALEXEeCUkfYLwzc8Zg" qualifier="containerType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_etoOsbEXEeCUkfYLwzc8Zg" outgoing="#_vHuCkLEXEeCUkfYLwzc8Zg"
        type="#_etoOsLEXEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_fkTYALEXEeCUkfYLwzc8Zg" name="container"
        incoming="#_tOQmkLEXEeCUkfYLwzc8Zg #_vHuCkLEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_g6XMALEXEeCUkfYLwzc8Zg" name="superType"
        incoming="#_s6f9ELEXEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_ly3zELEXEeCUkfYLwzc8Zg"
          valueExpression=".*(Collection|Map|List)" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_rSMigLEXEeCUkfYLwzc8Zg" name="gen"
        outgoing="#_s6f9ELEXEeCUkfYLwzc8Zg #_tOQmkLEXEeCUkfYLwzc8Zg" type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_3qb0MK0qEeC73qeN65GIrA" name="SingleDelegation" superPattern="#_CBRJ8K9AEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSPath" id="_NZdFYK0rEeC73qeN65GIrA" name="path1"
        source="#_-ljzQK0qEeC73qeN65GIrA" target="#_Cv-lQK0rEeC73qeN65GIrA"/>
    <connections xsi:type="specification:PSLink" id="_OD1fIK0rEeC73qeN65GIrA" name="link1"
        source="#_Cv-lQK0rEeC73qeN65GIrA" target="#_IDfGQK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess/targetFunction"/>
    <connections xsi:type="specification:PSLink" id="_Q4wd0K0rEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_-ljzQK0qEeC73qeN65GIrA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_RbLOYK0rEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_IDfGQK0rEeC73qeN65GIrA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_DwITUK0uEeC73qeN65GIrA" name="link4"
        source="#_-ljzQK0qEeC73qeN65GIrA" target="#_98ReUK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_HVYiwK0uEeC73qeN65GIrA" name="link5"
        source="#_IDfGQK0rEeC73qeN65GIrA" target="#_EfCnwK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_H_0m4K0uEeC73qeN65GIrA" name="link6"
        source="#_EfCnwK0uEeC73qeN65GIrA" target="#_5Ty3UK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_IMGvYK0uEeC73qeN65GIrA" name="link7"
        source="#_98ReUK0tEeC73qeN65GIrA" target="#_5Ty3UK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_MbjLUK0uEeC73qeN65GIrA" name="link9"
        source="#_JFxiUK0uEeC73qeN65GIrA" target="#_-ljzQK0qEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_MnxCYK0uEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_JFxiUK0uEeC73qeN65GIrA" qualifier="callerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_zlCvUK0uEeC73qeN65GIrA" name="link12"
        source="#_-ljzQK0qEeC73qeN65GIrA" target="#_haPy0K0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_CIJMYK0vEeC73qeN65GIrA" name="link13"
        source="#_haPy0K0uEeC73qeN65GIrA" target="#_q6h2QK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/variables#//Variable/typeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_CTuxUK0vEeC73qeN65GIrA" name="link14"
        source="#_q6h2QK0uEeC73qeN65GIrA" target="#_7-6kUK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_Cj57UK0vEeC73qeN65GIrA" name="link15"
        source="#_1nuYwK0uEeC73qeN65GIrA" target="#_5-Df0K0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/variables#//Variable/typeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_C1h20K0vEeC73qeN65GIrA" name="link16"
        source="#_5-Df0K0uEeC73qeN65GIrA" target="#_7-6kUK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_HPlnUK0vEeC73qeN65GIrA" name="link17"
        source="#_IDfGQK0rEeC73qeN65GIrA" target="#_1nuYwK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <nodes xsi:type="specification:PSAnnotation" id="_3qb0Ma0qEeC73qeN65GIrA" outgoing="#_Q4wd0K0rEeC73qeN65GIrA #_RbLOYK0rEeC73qeN65GIrA #_MnxCYK0uEeC73qeN65GIrA"
        type="#_3qb0MK0qEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSObject" id="_-ljzQK0qEeC73qeN65GIrA" name="caller"
        outgoing="#_NZdFYK0rEeC73qeN65GIrA #_DwITUK0uEeC73qeN65GIrA #_zlCvUK0uEeC73qeN65GIrA"
        incoming="#_Q4wd0K0rEeC73qeN65GIrA #_MbjLUK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_Cv-lQK0rEeC73qeN65GIrA" name="call"
        outgoing="#_OD1fIK0rEeC73qeN65GIrA" incoming="#_NZdFYK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <nodes xsi:type="specification:PSObject" id="_IDfGQK0rEeC73qeN65GIrA" name="callee"
        outgoing="#_HVYiwK0uEeC73qeN65GIrA #_HPlnUK0vEeC73qeN65GIrA" incoming="#_OD1fIK0rEeC73qeN65GIrA #_RbLOYK0rEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_5Ty3UK0tEeC73qeN65GIrA" name="returnType"
        incoming="#_H_0m4K0uEeC73qeN65GIrA #_IMGvYK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTType"/>
    <nodes xsi:type="specification:PSObject" id="_98ReUK0tEeC73qeN65GIrA" name="returnTypeAccessCaller"
        outgoing="#_IMGvYK0uEeC73qeN65GIrA" incoming="#_DwITUK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_EfCnwK0uEeC73qeN65GIrA" name="returnTypeAccessCallee"
        outgoing="#_H_0m4K0uEeC73qeN65GIrA" incoming="#_HVYiwK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_JFxiUK0uEeC73qeN65GIrA" name="callerClass"
        outgoing="#_MbjLUK0uEeC73qeN65GIrA" incoming="#_MnxCYK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_haPy0K0uEeC73qeN65GIrA" name="param"
        parents="#_H1z50K0vEeC73qeN65GIrA" outgoing="#_CIJMYK0vEeC73qeN65GIrA" incoming="#_zlCvUK0uEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_q6h2QK0uEeC73qeN65GIrA" name="paramAccess"
        parents="#_H1z50K0vEeC73qeN65GIrA" outgoing="#_CTuxUK0vEeC73qeN65GIrA" incoming="#_CIJMYK0vEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_1nuYwK0uEeC73qeN65GIrA" name="param2"
        parents="#_H1z50K0vEeC73qeN65GIrA" outgoing="#_Cj57UK0vEeC73qeN65GIrA" incoming="#_HPlnUK0vEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_5-Df0K0uEeC73qeN65GIrA" name="paramAccess2"
        parents="#_H1z50K0vEeC73qeN65GIrA" outgoing="#_C1h20K0vEeC73qeN65GIrA" incoming="#_Cj57UK0vEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_7-6kUK0uEeC73qeN65GIrA" name="paramType"
        parents="#_H1z50K0vEeC73qeN65GIrA" incoming="#_CTuxUK0vEeC73qeN65GIrA #_C1h20K0vEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/types#//GASTType"/>
    <combinedFragments id="_H1z50K0vEeC73qeN65GIrA" name="frag1" children="#_haPy0K0uEeC73qeN65GIrA #_q6h2QK0uEeC73qeN65GIrA #_1nuYwK0uEeC73qeN65GIrA #_5-Df0K0uEeC73qeN65GIrA #_7-6kUK0uEeC73qeN65GIrA"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_zz1goK0rEeC73qeN65GIrA" name="MultiDelegation" superPattern="#_CBRJ8K9AEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_JyRG0K0sEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_BnCKwK0sEeC73qeN65GIrA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_KA_fUK0sEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_-3e0QK0rEeC73qeN65GIrA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSPath" id="_KmFJYK0sEeC73qeN65GIrA" name="path1"
        source="#_BnCKwK0sEeC73qeN65GIrA" target="#_49rvUK0rEeC73qeN65GIrA"/>
    <connections xsi:type="specification:PSLink" id="_LfNw0K0sEeC73qeN65GIrA" name="link3"
        source="#_89aUQK0rEeC73qeN65GIrA" target="#_-3e0QK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess/targetFunction"/>
    <connections xsi:type="specification:PSLink" id="_Eg0l4K0tEeC73qeN65GIrA" name="link6"
        source="#_DDHRwK0tEeC73qeN65GIrA" target="#_BnCKwK0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_I0xuYK0tEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_DDHRwK0tEeC73qeN65GIrA" qualifier="callerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSPath" id="_VSWCUK0tEeC73qeN65GIrA" name="path3"
        source="#_13ryQK0sEeC73qeN65GIrA" target="#_89aUQK0rEeC73qeN65GIrA"/>
    <connections xsi:type="specification:PSPath" id="_4oxZ0K0sEeC73qeN65GIrA" name="path2"
        source="#_49rvUK0rEeC73qeN65GIrA" target="#_13ryQK0sEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_zz5LAK0rEeC73qeN65GIrA" outgoing="#_JyRG0K0sEeC73qeN65GIrA #_KA_fUK0sEeC73qeN65GIrA #_I0xuYK0tEeC73qeN65GIrA"
        type="#_zz1goK0rEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSObject" id="_49rvUK0rEeC73qeN65GIrA" name="loop"
        outgoing="#_4oxZ0K0sEeC73qeN65GIrA" incoming="#_KmFJYK0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/statements#//LoopStatement"/>
    <nodes xsi:type="specification:PSObject" id="_89aUQK0rEeC73qeN65GIrA" name="call"
        outgoing="#_LfNw0K0sEeC73qeN65GIrA" incoming="#_VSWCUK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <nodes xsi:type="specification:PSObject" id="_-3e0QK0rEeC73qeN65GIrA" name="callee"
        incoming="#_KA_fUK0sEeC73qeN65GIrA #_LfNw0K0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_BnCKwK0sEeC73qeN65GIrA" name="caller"
        outgoing="#_KmFJYK0sEeC73qeN65GIrA" incoming="#_JyRG0K0sEeC73qeN65GIrA #_Eg0l4K0tEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_DDHRwK0tEeC73qeN65GIrA" name="callerClass"
        outgoing="#_Eg0l4K0tEeC73qeN65GIrA" incoming="#_I0xuYK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_13ryQK0sEeC73qeN65GIrA" name="block"
        outgoing="#_VSWCUK0tEeC73qeN65GIrA" incoming="#_4oxZ0K0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/statements#//Statement"/>
  </patternSpecifications>
  <patternSpecifications id="_Y_pvgLEoEeCUkfYLwzc8Zg" name="CreateMethod">
    <connections xsi:type="specification:PSLink" id="_kyXIkLEoEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_Y_pvgbEoEeCUkfYLwzc8Zg" target="#_ak-HgLEoEeCUkfYLwzc8Zg" qualifier="factoryMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_k_j3ELEoEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_Y_pvgbEoEeCUkfYLwzc8Zg" target="#_a1mkgLEoEeCUkfYLwzc8Zg" qualifier="returnType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_q4KCILEoEeCUkfYLwzc8Zg" name="link3"
        source="#_ak-HgLEoEeCUkfYLwzc8Zg" target="#_oCHCELEoEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_rhHfkLEoEeCUkfYLwzc8Zg" name="link4"
        source="#_oCHCELEoEeCUkfYLwzc8Zg" target="#_a1mkgLEoEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_oYlPELEtEeCUkfYLwzc8Zg" name="link5"
        source="#_i1HmgLEtEeCUkfYLwzc8Zg" target="#_lIhMgLEtEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSPath" id="_vXNOELEtEeCUkfYLwzc8Zg" name="path1"
        source="#_ak-HgLEoEeCUkfYLwzc8Zg" target="#_i1HmgLEtEeCUkfYLwzc8Zg"/>
    <connections xsi:type="specification:PSLink" id="_xqyykLEzEeCUkfYLwzc8Zg" name="link6"
        source="#_a1mkgLEoEeCUkfYLwzc8Zg" target="#_lIhMgLEtEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/constructors"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Y_pvgbEoEeCUkfYLwzc8Zg" outgoing="#_kyXIkLEoEeCUkfYLwzc8Zg #_k_j3ELEoEeCUkfYLwzc8Zg"
        type="#_Y_pvgLEoEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_ak-HgLEoEeCUkfYLwzc8Zg" name="creatingMethod"
        outgoing="#_q4KCILEoEeCUkfYLwzc8Zg #_vXNOELEtEeCUkfYLwzc8Zg" incoming="#_kyXIkLEoEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_a1mkgLEoEeCUkfYLwzc8Zg" name="returnType"
        outgoing="#_xqyykLEzEeCUkfYLwzc8Zg" incoming="#_k_j3ELEoEeCUkfYLwzc8Zg #_rhHfkLEoEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_oCHCELEoEeCUkfYLwzc8Zg" name="returns"
        outgoing="#_rhHfkLEoEeCUkfYLwzc8Zg" incoming="#_q4KCILEoEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_i1HmgLEtEeCUkfYLwzc8Zg" name="constructorCall"
        outgoing="#_oYlPELEtEeCUkfYLwzc8Zg" incoming="#_vXNOELEtEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <nodes xsi:type="specification:PSObject" id="_lIhMgLEtEeCUkfYLwzc8Zg" name="constructor"
        incoming="#_oYlPELEtEeCUkfYLwzc8Zg #_xqyykLEzEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Constructor"/>
  </patternSpecifications>
  <patternSpecifications id="_ijcUkLEVEeCUkfYLwzc8Zg" name="TemplateMethod">
    <connections xsi:type="specification:PSLink" id="_Gy8BELEgEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_ijcUkbEVEeCUkfYLwzc8Zg" target="#_4gP-kLEfEeCUkfYLwzc8Zg" qualifier="abstractClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_G-OrELEgEeCUkfYLwzc8Zg" name="link2"
        source="#_FJNqMLEgEeCUkfYLwzc8Zg" target="#_4gP-kLEfEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HJI6kLEgEeCUkfYLwzc8Zg" name="link3"
        source="#_FJNqMLEgEeCUkfYLwzc8Zg" target="#_5BWggLEfEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HZd1kLEgEeCUkfYLwzc8Zg" name="link4"
        source="#_4gP-kLEfEeCUkfYLwzc8Zg" target="#_5SIugLEfEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_mcEyALEgEeCUkfYLwzc8Zg" name="link5"
        source="#_k_dqELEgEeCUkfYLwzc8Zg" target="#_5godgLEfEeCUkfYLwzc8Zg" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_mlxgkLEgEeCUkfYLwzc8Zg" name="link6"
        source="#_k_dqELEgEeCUkfYLwzc8Zg" target="#_4wCHALEfEeCUkfYLwzc8Zg" qualifier="overriding"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_pq7ToLEgEeCUkfYLwzc8Zg" name="link7"
        source="#_5BWggLEfEeCUkfYLwzc8Zg" target="#_4wCHALEfEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_uzS7oLEgEeCUkfYLwzc8Zg" name="link8"
        source="#_tNMggLEgEeCUkfYLwzc8Zg" target="#_5godgLEfEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSPath" id="_wPEWELEgEeCUkfYLwzc8Zg" name="path1"
        source="#_5SIugLEfEeCUkfYLwzc8Zg" target="#_tNMggLEgEeCUkfYLwzc8Zg"/>
    <connections xsi:type="specification:PSLink" id="_ItYfELEhEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_ijcUkbEVEeCUkfYLwzc8Zg" target="#_5SIugLEfEeCUkfYLwzc8Zg" qualifier="templateMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_ijcUkbEVEeCUkfYLwzc8Zg" outgoing="#_Gy8BELEgEeCUkfYLwzc8Zg #_ItYfELEhEeCUkfYLwzc8Zg"
        type="#_ijcUkLEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_4gP-kLEfEeCUkfYLwzc8Zg" name="abstractClass"
        outgoing="#_HZd1kLEgEeCUkfYLwzc8Zg" incoming="#_Gy8BELEgEeCUkfYLwzc8Zg #_G-OrELEgEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_dbCPULEgEeCUkfYLwzc8Zg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_4wCHALEfEeCUkfYLwzc8Zg" name="concretePrimitive"
        parents="#_3clFoLEgEeCUkfYLwzc8Zg" incoming="#_mlxgkLEgEeCUkfYLwzc8Zg #_pq7ToLEgEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_5BWggLEfEeCUkfYLwzc8Zg" name="concreteClass"
        outgoing="#_pq7ToLEgEeCUkfYLwzc8Zg" incoming="#_HJI6kLEgEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_eNPIULEgEeCUkfYLwzc8Zg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_5SIugLEfEeCUkfYLwzc8Zg" name="templateMethod"
        outgoing="#_wPEWELEgEeCUkfYLwzc8Zg" incoming="#_HZd1kLEgEeCUkfYLwzc8Zg #_ItYfELEhEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_dqRlMLEgEeCUkfYLwzc8Zg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_5godgLEfEeCUkfYLwzc8Zg" name="primitive"
        parents="#_3clFoLEgEeCUkfYLwzc8Zg" incoming="#_mcEyALEgEeCUkfYLwzc8Zg #_uzS7oLEgEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_d4fAULEgEeCUkfYLwzc8Zg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_FJNqMLEgEeCUkfYLwzc8Zg" name="gen"
        outgoing="#_G-OrELEgEeCUkfYLwzc8Zg #_HJI6kLEgEeCUkfYLwzc8Zg" type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_k_dqELEgEeCUkfYLwzc8Zg" name="om"
        parents="#_3clFoLEgEeCUkfYLwzc8Zg" outgoing="#_mcEyALEgEeCUkfYLwzc8Zg #_mlxgkLEgEeCUkfYLwzc8Zg"
        type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_tNMggLEgEeCUkfYLwzc8Zg" name="call"
        parents="#_3clFoLEgEeCUkfYLwzc8Zg" outgoing="#_uzS7oLEgEeCUkfYLwzc8Zg" incoming="#_wPEWELEgEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <combinedFragments id="_3clFoLEgEeCUkfYLwzc8Zg" name="frag1" children="#_4wCHALEfEeCUkfYLwzc8Zg #_5godgLEfEeCUkfYLwzc8Zg #_k_dqELEgEeCUkfYLwzc8Zg #_tNMggLEgEeCUkfYLwzc8Zg"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_6ritQLEmEeCUkfYLwzc8Zg" name="AbstractFactory">
    <connections xsi:type="specification:PSLink" id="_OGHPkLEnEeCUkfYLwzc8Zg" name="link1"
        source="#_KWx3ELEnEeCUkfYLwzc8Zg" target="#_8WZFgLEmEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_OQabELEnEeCUkfYLwzc8Zg" name="link2"
        source="#_KWx3ELEnEeCUkfYLwzc8Zg" target="#_82qhALEmEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_OeJVELEnEeCUkfYLwzc8Zg" name="link3"
        source="#_KyUjkLEnEeCUkfYLwzc8Zg" target="#_9TNTELEmEeCUkfYLwzc8Zg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_OoJloLEnEeCUkfYLwzc8Zg" name="link4"
        source="#_KyUjkLEnEeCUkfYLwzc8Zg" target="#_9lmDkLEmEeCUkfYLwzc8Zg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_PCFIkLEnEeCUkfYLwzc8Zg" name="annotatedElement"
        source="#_6ritQbEmEeCUkfYLwzc8Zg" target="#_8WZFgLEmEeCUkfYLwzc8Zg" qualifier="abstractFactory"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_btUFELEnEeCUkfYLwzc8Zg" name="link6"
        source="#_8WZFgLEmEeCUkfYLwzc8Zg" target="#_Y2RmgLEnEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_iQeQELEnEeCUkfYLwzc8Zg" name="link7"
        source="#_Y2RmgLEnEeCUkfYLwzc8Zg" target="#_e2akALEnEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_ibTnELEnEeCUkfYLwzc8Zg" name="link8"
        source="#_e2akALEnEeCUkfYLwzc8Zg" target="#_9TNTELEmEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_r3yIkLEnEeCUkfYLwzc8Zg" name="link9"
        source="#_82qhALEmEeCUkfYLwzc8Zg" target="#_j6SGgLEnEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_sBA9ELEnEeCUkfYLwzc8Zg" name="link10"
        source="#_j6SGgLEnEeCUkfYLwzc8Zg" target="#_kSBRELEnEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_sJzFoLEnEeCUkfYLwzc8Zg" name="link11"
        source="#_kSBRELEnEeCUkfYLwzc8Zg" target="#_9lmDkLEmEeCUkfYLwzc8Zg" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_wK0rELEnEeCUkfYLwzc8Zg" name="link12"
        source="#_ude_gLEnEeCUkfYLwzc8Zg" target="#_Y2RmgLEnEeCUkfYLwzc8Zg" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_wdqukLEnEeCUkfYLwzc8Zg" name="link13"
        source="#_ude_gLEnEeCUkfYLwzc8Zg" target="#_j6SGgLEnEeCUkfYLwzc8Zg" qualifier="overriding"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_C95coLE1EeCUkfYLwzc8Zg" name="link14"
        source="#_-kInELE0EeCUkfYLwzc8Zg" target="#_j6SGgLEnEeCUkfYLwzc8Zg" qualifier="factoryMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_El9moLE1EeCUkfYLwzc8Zg" name="link15"
        source="#_-kInELE0EeCUkfYLwzc8Zg" target="#_9lmDkLEmEeCUkfYLwzc8Zg" qualifier="returnType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_6ritQbEmEeCUkfYLwzc8Zg" outgoing="#_PCFIkLEnEeCUkfYLwzc8Zg"
        type="#_6ritQLEmEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_8WZFgLEmEeCUkfYLwzc8Zg" name="abstractFactory"
        outgoing="#_btUFELEnEeCUkfYLwzc8Zg" incoming="#_OGHPkLEnEeCUkfYLwzc8Zg #_PCFIkLEnEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_I8t3YLEoEeCUkfYLwzc8Zg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_82qhALEmEeCUkfYLwzc8Zg" name="concreteFactory"
        parents="#_L9nJoLE1EeCUkfYLwzc8Zg" outgoing="#_r3yIkLEnEeCUkfYLwzc8Zg" incoming="#_OQabELEnEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_KRtUYLEoEeCUkfYLwzc8Zg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_9TNTELEmEeCUkfYLwzc8Zg" name="abstractProduct"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" incoming="#_OeJVELEnEeCUkfYLwzc8Zg #_ibTnELEnEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_UVUuYLEoEeCUkfYLwzc8Zg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_9lmDkLEmEeCUkfYLwzc8Zg" name="concreteProduct"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" incoming="#_OoJloLEnEeCUkfYLwzc8Zg #_sJzFoLEnEeCUkfYLwzc8Zg #_El9moLE1EeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_R60a0LEoEeCUkfYLwzc8Zg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_KWx3ELEnEeCUkfYLwzc8Zg" name="gen1"
        parents="#_L9nJoLE1EeCUkfYLwzc8Zg" outgoing="#_OGHPkLEnEeCUkfYLwzc8Zg #_OQabELEnEeCUkfYLwzc8Zg"
        type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_KyUjkLEnEeCUkfYLwzc8Zg" name="gen2"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" outgoing="#_OeJVELEnEeCUkfYLwzc8Zg #_OoJloLEnEeCUkfYLwzc8Zg"
        type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_Y2RmgLEnEeCUkfYLwzc8Zg" name="abstractCreate"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" outgoing="#_iQeQELEnEeCUkfYLwzc8Zg" incoming="#_btUFELEnEeCUkfYLwzc8Zg #_wK0rELEnEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_NfGdcLEoEeCUkfYLwzc8Zg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_e2akALEnEeCUkfYLwzc8Zg" name="returns1"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" outgoing="#_ibTnELEnEeCUkfYLwzc8Zg" incoming="#_iQeQELEnEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_j6SGgLEnEeCUkfYLwzc8Zg" name="concreteCreate"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" outgoing="#_sBA9ELEnEeCUkfYLwzc8Zg" incoming="#_r3yIkLEnEeCUkfYLwzc8Zg #_wdqukLEnEeCUkfYLwzc8Zg #_C95coLE1EeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_QSS90LEoEeCUkfYLwzc8Zg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_kSBRELEnEeCUkfYLwzc8Zg" name="returns2"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" outgoing="#_sJzFoLEnEeCUkfYLwzc8Zg" incoming="#_sBA9ELEnEeCUkfYLwzc8Zg"
        instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSAnnotation" id="_ude_gLEnEeCUkfYLwzc8Zg" name="om"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg" outgoing="#_wK0rELEnEeCUkfYLwzc8Zg #_wdqukLEnEeCUkfYLwzc8Zg"
        type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_-kInELE0EeCUkfYLwzc8Zg" name="createMethod"
        parents="#_FoVpoLE1EeCUkfYLwzc8Zg #_L9nJoLE1EeCUkfYLwzc8Zg" outgoing="#_C95coLE1EeCUkfYLwzc8Zg #_El9moLE1EeCUkfYLwzc8Zg"
        type="#_Y_pvgLEoEeCUkfYLwzc8Zg"/>
    <combinedFragments id="_FoVpoLE1EeCUkfYLwzc8Zg" name="frag1" children="#_9TNTELEmEeCUkfYLwzc8Zg #_Y2RmgLEnEeCUkfYLwzc8Zg #_e2akALEnEeCUkfYLwzc8Zg #_9lmDkLEmEeCUkfYLwzc8Zg #_KyUjkLEnEeCUkfYLwzc8Zg #_j6SGgLEnEeCUkfYLwzc8Zg #_kSBRELEnEeCUkfYLwzc8Zg #_ude_gLEnEeCUkfYLwzc8Zg #_-kInELE0EeCUkfYLwzc8Zg"
        kind="SET"/>
    <combinedFragments id="_L9nJoLE1EeCUkfYLwzc8Zg" name="frag2" children="#_82qhALEmEeCUkfYLwzc8Zg #_KWx3ELEnEeCUkfYLwzc8Zg #_-kInELE0EeCUkfYLwzc8Zg"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_lOThQLF2EeCDAa1tErEpvg" name="FactoryMethod">
    <connections xsi:type="specification:PSLink" id="_IUt_cLF5EeCDAa1tErEpvg" name="annotatedElement"
        source="#_lOThQbF2EeCDAa1tErEpvg" target="#_qNNZULF4EeCDAa1tErEpvg" qualifier="abstractProduct"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_IfIfsLF5EeCDAa1tErEpvg" name="annotatedElement"
        source="#_lOThQbF2EeCDAa1tErEpvg" target="#_qgjMELF4EeCDAa1tErEpvg" qualifier="concreteProduct"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Ipsw8LF5EeCDAa1tErEpvg" name="annotatedElement"
        source="#_lOThQbF2EeCDAa1tErEpvg" target="#_rxCjkLF4EeCDAa1tErEpvg" qualifier="concreteFactoryMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_IzEvYLF5EeCDAa1tErEpvg" name="annotatedElement"
        source="#_lOThQbF2EeCDAa1tErEpvg" target="#_rgOgYLF4EeCDAa1tErEpvg" qualifier="abstractFactoryMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_X0m04LF5EeCDAa1tErEpvg" name="link5"
        source="#_WeKmYLF5EeCDAa1tErEpvg" target="#_rgOgYLF4EeCDAa1tErEpvg" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_YAD28LF5EeCDAa1tErEpvg" name="link6"
        source="#_WeKmYLF5EeCDAa1tErEpvg" target="#_rxCjkLF4EeCDAa1tErEpvg" qualifier="overriding"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_cuXZILF5EeCDAa1tErEpvg" name="link7"
        source="#_WHjdMLF5EeCDAa1tErEpvg" target="#_qNNZULF4EeCDAa1tErEpvg" qualifier="superType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_c47qYLF5EeCDAa1tErEpvg" name="link8"
        source="#_WHjdMLF5EeCDAa1tErEpvg" target="#_qgjMELF4EeCDAa1tErEpvg" qualifier="subType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_gt9bMLF5EeCDAa1tErEpvg" name="link9"
        source="#_rgOgYLF4EeCDAa1tErEpvg" target="#_eFQTkLF5EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_g4-YYLF5EeCDAa1tErEpvg" name="link10"
        source="#_eFQTkLF5EeCDAa1tErEpvg" target="#_qNNZULF4EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedClass"/>
    <connections xsi:type="specification:PSLink" id="_tVWZgLF5EeCDAa1tErEpvg" name="link11"
        source="#_sBSwoLF5EeCDAa1tErEpvg" target="#_rxCjkLF4EeCDAa1tErEpvg" qualifier="factoryMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_tjMBMLF5EeCDAa1tErEpvg" name="link12"
        source="#_sBSwoLF5EeCDAa1tErEpvg" target="#_qgjMELF4EeCDAa1tErEpvg" qualifier="returnType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_lOThQbF2EeCDAa1tErEpvg" outgoing="#_IUt_cLF5EeCDAa1tErEpvg #_IfIfsLF5EeCDAa1tErEpvg #_Ipsw8LF5EeCDAa1tErEpvg #_IzEvYLF5EeCDAa1tErEpvg"
        type="#_lOThQLF2EeCDAa1tErEpvg"/>
    <nodes xsi:type="specification:PSObject" id="_qNNZULF4EeCDAa1tErEpvg" name="abstractProduct"
        incoming="#_IUt_cLF5EeCDAa1tErEpvg #_cuXZILF5EeCDAa1tErEpvg #_g4-YYLF5EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_3pkQwLF4EeCDAa1tErEpvg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_qgjMELF4EeCDAa1tErEpvg" name="concreteProduct"
        incoming="#_IfIfsLF5EeCDAa1tErEpvg #_c47qYLF5EeCDAa1tErEpvg #_tjMBMLF5EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_34v8QLF4EeCDAa1tErEpvg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_rgOgYLF4EeCDAa1tErEpvg" name="abstractFactoryMethod"
        outgoing="#_gt9bMLF5EeCDAa1tErEpvg" incoming="#_IzEvYLF5EeCDAa1tErEpvg #_X0m04LF5EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_4I9igLF4EeCDAa1tErEpvg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_rxCjkLF4EeCDAa1tErEpvg" name="concreteFactoryMethod"
        incoming="#_Ipsw8LF5EeCDAa1tErEpvg #_YAD28LF5EeCDAa1tErEpvg #_tVWZgLF5EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_4YIm8LF4EeCDAa1tErEpvg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_WHjdMLF5EeCDAa1tErEpvg" name="gen"
        outgoing="#_cuXZILF5EeCDAa1tErEpvg #_c47qYLF5EeCDAa1tErEpvg" type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_WeKmYLF5EeCDAa1tErEpvg" name="om"
        outgoing="#_X0m04LF5EeCDAa1tErEpvg #_YAD28LF5EeCDAa1tErEpvg" type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_eFQTkLF5EeCDAa1tErEpvg" name="returnTypeAccess"
        outgoing="#_g4-YYLF5EeCDAa1tErEpvg" incoming="#_gt9bMLF5EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSAnnotation" id="_sBSwoLF5EeCDAa1tErEpvg" name="create"
        outgoing="#_tVWZgLF5EeCDAa1tErEpvg #_tjMBMLF5EeCDAa1tErEpvg" type="#_Y_pvgLEoEeCUkfYLwzc8Zg"/>
  </patternSpecifications>
  <patternSpecifications id="_6Rk_YLF5EeCDAa1tErEpvg" name="State">
    <connections xsi:type="specification:PSLink" id="_IiAYELF6EeCDAa1tErEpvg" name="annotatedElement"
        source="#_6Rk_YbF5EeCDAa1tErEpvg" target="#_B7ySALF6EeCDAa1tErEpvg" qualifier="contextClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_IvGY4LF6EeCDAa1tErEpvg" name="annotatedElement"
        source="#_6Rk_YbF5EeCDAa1tErEpvg" target="#_CQUXkLF6EeCDAa1tErEpvg" qualifier="stateClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_I9rAYLF6EeCDAa1tErEpvg" name="annotatedElement"
        source="#_6Rk_YbF5EeCDAa1tErEpvg" target="#_CxDGELF6EeCDAa1tErEpvg" qualifier="requestMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_JIiMkLF6EeCDAa1tErEpvg" name="annotatedElement"
        source="#_6Rk_YbF5EeCDAa1tErEpvg" target="#_Cgru0LF6EeCDAa1tErEpvg" qualifier="handleMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_JXkHELF6EeCDAa1tErEpvg" name="link5"
        source="#_B7ySALF6EeCDAa1tErEpvg" target="#_CxDGELF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_JohUMLF6EeCDAa1tErEpvg" name="link6"
        source="#_CQUXkLF6EeCDAa1tErEpvg" target="#_Cgru0LF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_SKYAgLF6EeCDAa1tErEpvg" name="link7"
        source="#_QwXgoLF6EeCDAa1tErEpvg" target="#_CxDGELF6EeCDAa1tErEpvg" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_SXBVYLF6EeCDAa1tErEpvg" name="link8"
        source="#_QwXgoLF6EeCDAa1tErEpvg" target="#_Cgru0LF6EeCDAa1tErEpvg" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_fqAOYLF6EeCDAa1tErEpvg" name="link9"
        source="#_b8QxULF6EeCDAa1tErEpvg" target="#_cM7DgLF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_fzhWwLF6EeCDAa1tErEpvg" name="link10"
        source="#_cM7DgLF6EeCDAa1tErEpvg" target="#_CQUXkLF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_gTXUcLF6EeCDAa1tErEpvg" name="link11"
        source="#_B7ySALF6EeCDAa1tErEpvg" target="#_b8QxULF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_POgP0LF7EeCDAa1tErEpvg" name="link12"
        source="#_Cgru0LF6EeCDAa1tErEpvg" target="#_MpEeoLF7EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_Pf6I4LF7EeCDAa1tErEpvg" name="link13"
        source="#_MpEeoLF7EeCDAa1tErEpvg" target="#_B7ySALF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_UM4OULF7EeCDAa1tErEpvg" name="link14"
        source="#_RiYW8LF7EeCDAa1tErEpvg" target="#_B7ySALF6EeCDAa1tErEpvg" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_UXSukLF7EeCDAa1tErEpvg" name="link15"
        source="#_RiYW8LF7EeCDAa1tErEpvg" target="#_CQUXkLF6EeCDAa1tErEpvg" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_C-_XQLGBEeCDAa1tErEpvg" name="link16"
        source="#_BLFwsLGBEeCDAa1tErEpvg" target="#_b8QxULF6EeCDAa1tErEpvg" qualifier="setMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_6Rk_YbF5EeCDAa1tErEpvg" outgoing="#_IiAYELF6EeCDAa1tErEpvg #_IvGY4LF6EeCDAa1tErEpvg #_I9rAYLF6EeCDAa1tErEpvg #_JIiMkLF6EeCDAa1tErEpvg"
        type="#_6Rk_YLF5EeCDAa1tErEpvg"/>
    <nodes xsi:type="specification:PSObject" id="_B7ySALF6EeCDAa1tErEpvg" name="contextClass"
        outgoing="#_JXkHELF6EeCDAa1tErEpvg #_gTXUcLF6EeCDAa1tErEpvg" incoming="#_IiAYELF6EeCDAa1tErEpvg #_Pf6I4LF7EeCDAa1tErEpvg #_UM4OULF7EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_CQUXkLF6EeCDAa1tErEpvg" name="stateClass"
        outgoing="#_JohUMLF6EeCDAa1tErEpvg" incoming="#_IvGY4LF6EeCDAa1tErEpvg #_fzhWwLF6EeCDAa1tErEpvg #_UXSukLF7EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_ZAXA4LF6EeCDAa1tErEpvg"
          additional="true" valueExpression=".*(s|S)tate.*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_u2-u4LF6EeCDAa1tErEpvg"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_Cgru0LF6EeCDAa1tErEpvg" name="handle"
        outgoing="#_POgP0LF7EeCDAa1tErEpvg" incoming="#_JIiMkLF6EeCDAa1tErEpvg #_JohUMLF6EeCDAa1tErEpvg #_SXBVYLF6EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_GEohQLF7EeCDAa1tErEpvg"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_CxDGELF6EeCDAa1tErEpvg" name="request"
        incoming="#_I9rAYLF6EeCDAa1tErEpvg #_JXkHELF6EeCDAa1tErEpvg #_SKYAgLF6EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_QwXgoLF6EeCDAa1tErEpvg" name="del"
        outgoing="#_SKYAgLF6EeCDAa1tErEpvg #_SXBVYLF6EeCDAa1tErEpvg" type="#_CBRJ8K9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_b8QxULF6EeCDAa1tErEpvg" name="setStates"
        outgoing="#_fqAOYLF6EeCDAa1tErEpvg" incoming="#_gTXUcLF6EeCDAa1tErEpvg #_C-_XQLGBEeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_cM7DgLF6EeCDAa1tErEpvg" name="param"
        outgoing="#_fzhWwLF6EeCDAa1tErEpvg" incoming="#_fqAOYLF6EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_MpEeoLF7EeCDAa1tErEpvg" name="contextParam"
        modifier="ADDITIONAL" outgoing="#_Pf6I4LF7EeCDAa1tErEpvg" incoming="#_POgP0LF7EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSAnnotation" id="_RiYW8LF7EeCDAa1tErEpvg" name="ref"
        outgoing="#_UM4OULF7EeCDAa1tErEpvg #_UXSukLF7EeCDAa1tErEpvg" type="#_emQtULEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSAnnotation" id="_BLFwsLGBEeCDAa1tErEpvg" name="setter"
        modifier="ADDITIONAL" outgoing="#_C-_XQLGBEeCDAa1tErEpvg" type="#_bUtzsLF7EeCDAa1tErEpvg"/>
  </patternSpecifications>
  <patternSpecifications id="_bUtzsLF7EeCDAa1tErEpvg" name="SetMethod">
    <connections xsi:type="specification:PSLink" id="_vu9DQLF7EeCDAa1tErEpvg" name="annotatedElement"
        source="#_bUtzsbF7EeCDAa1tErEpvg" target="#_ewJ_0LF7EeCDAa1tErEpvg" qualifier="setMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_y8sxoLF7EeCDAa1tErEpvg" name="link2"
        source="#_ewJ_0LF7EeCDAa1tErEpvg" target="#_xeFekLF7EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_-OTzULF8EeCDAa1tErEpvg" name="link3"
        source="#_xeFekLF7EeCDAa1tErEpvg" target="#_7CohgLF8EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_-1u_sLF8EeCDAa1tErEpvg" name="link4"
        source="#_5u3zkLF8EeCDAa1tErEpvg" target="#_7CohgLF8EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_GTvCULF9EeCDAa1tErEpvg" name="link5"
        source="#_DOnEcLF9EeCDAa1tErEpvg" target="#_5u3zkLF8EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_GffmYLF9EeCDAa1tErEpvg" name="link6"
        source="#_DOnEcLF9EeCDAa1tErEpvg" target="#_ewJ_0LF7EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_MnqegLF9EeCDAa1tErEpvg" name="link7"
        source="#_KoKE4LF9EeCDAa1tErEpvg" target="#_5u3zkLF8EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSPath" id="_NiRsoLF9EeCDAa1tErEpvg" name="path1"
        source="#_ewJ_0LF7EeCDAa1tErEpvg" target="#_KoKE4LF9EeCDAa1tErEpvg"/>
    <connections xsi:type="specification:PSLink" id="_xSeusLF_EeCDAa1tErEpvg" name="annotatedElement"
        source="#_bUtzsbF7EeCDAa1tErEpvg" target="#_5u3zkLF8EeCDAa1tErEpvg" qualifier="setField"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_bUtzsbF7EeCDAa1tErEpvg" outgoing="#_vu9DQLF7EeCDAa1tErEpvg #_xSeusLF_EeCDAa1tErEpvg"
        type="#_bUtzsLF7EeCDAa1tErEpvg"/>
    <nodes xsi:type="specification:PSObject" id="_ewJ_0LF7EeCDAa1tErEpvg" name="setter"
        outgoing="#_y8sxoLF7EeCDAa1tErEpvg #_NiRsoLF9EeCDAa1tErEpvg" incoming="#_vu9DQLF7EeCDAa1tErEpvg #_GffmYLF9EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_iRsz8LF7EeCDAa1tErEpvg"
          valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/static"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_jzTJkLF7EeCDAa1tErEpvg"
          additional="true" valueExpression="set.*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_mjTMALF7EeCDAa1tErEpvg"
          valueExpression="VISIBILITYPRIVAT" operator="UNEQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_xeFekLF7EeCDAa1tErEpvg" name="param"
        outgoing="#_-OTzULF8EeCDAa1tErEpvg" incoming="#_y8sxoLF7EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_5u3zkLF8EeCDAa1tErEpvg" name="field"
        outgoing="#_-1u_sLF8EeCDAa1tErEpvg" incoming="#_GTvCULF9EeCDAa1tErEpvg #_MnqegLF9EeCDAa1tErEpvg #_xSeusLF_EeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_7CohgLF8EeCDAa1tErEpvg" name="fieldType"
        incoming="#_-OTzULF8EeCDAa1tErEpvg #_-1u_sLF8EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTType"/>
    <nodes xsi:type="specification:PSObject" id="_DOnEcLF9EeCDAa1tErEpvg" name="owningClass"
        outgoing="#_GTvCULF9EeCDAa1tErEpvg #_GffmYLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_KoKE4LF9EeCDAa1tErEpvg" name="fieldAccess"
        outgoing="#_MnqegLF9EeCDAa1tErEpvg" incoming="#_NiRsoLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_Pb-ZMLF9EeCDAa1tErEpvg"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/accesses#//VariableAccess/write"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_neldoLF9EeCDAa1tErEpvg" name="GetMethod" subPatterns="#_ixwVsLw_EeCmFO4k1VVXaw">
    <connections xsi:type="specification:PSLink" id="_43aKoLF9EeCDAa1tErEpvg" name="link1"
        source="#_qQEt0LF9EeCDAa1tErEpvg" target="#_qkAWcLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_5Q2lYLF9EeCDAa1tErEpvg" name="link2"
        source="#_rQfpALF9EeCDAa1tErEpvg" target="#_qkAWcLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_5w1tALF9EeCDAa1tErEpvg" name="link3"
        source="#_pEC_kLF9EeCDAa1tErEpvg" target="#_pnAisLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_59MG8LF9EeCDAa1tErEpvg" name="link4"
        source="#_pEC_kLF9EeCDAa1tErEpvg" target="#_rQfpALF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_6KSHwLF9EeCDAa1tErEpvg" name="link5"
        source="#_pnAisLF9EeCDAa1tErEpvg" target="#_qQEt0LF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_6WxroLF9EeCDAa1tErEpvg" name="annotatedElement"
        source="#_neldobF9EeCDAa1tErEpvg" target="#_pnAisLF9EeCDAa1tErEpvg" qualifier="getMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_loX4ALF_EeCDAa1tErEpvg" name="link7"
        source="#_pnAisLF9EeCDAa1tErEpvg" target="#_kO91ELF_EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_qmmskLF_EeCDAa1tErEpvg" name="annotatedElement"
        source="#_neldobF9EeCDAa1tErEpvg" target="#_rQfpALF9EeCDAa1tErEpvg" qualifier="returnedField"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSPath" id="_EurBoLGAEeCDAa1tErEpvg" name="path1"
        source="#_pnAisLF9EeCDAa1tErEpvg" target="#_A3HhQLGAEeCDAa1tErEpvg"/>
    <connections xsi:type="specification:PSLink" id="_Fyq1QLGAEeCDAa1tErEpvg" name="link10"
        source="#_q00ZoLF9EeCDAa1tErEpvg" target="#_rQfpALF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_F6cEwLxCEeCmFO4k1VVXaw" name="link10"
        source="#_A3HhQLGAEeCDAa1tErEpvg" target="#_q00ZoLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/statements#//Statement/accesses"/>
    <nodes xsi:type="specification:PSAnnotation" id="_neldobF9EeCDAa1tErEpvg" outgoing="#_6WxroLF9EeCDAa1tErEpvg #_qmmskLF_EeCDAa1tErEpvg"
        type="#_neldoLF9EeCDAa1tErEpvg"/>
    <nodes xsi:type="specification:PSObject" id="_pEC_kLF9EeCDAa1tErEpvg" name="owningClass"
        outgoing="#_5w1tALF9EeCDAa1tErEpvg #_59MG8LF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_pnAisLF9EeCDAa1tErEpvg" name="getter"
        outgoing="#_6KSHwLF9EeCDAa1tErEpvg #_loX4ALF_EeCDAa1tErEpvg #_EurBoLGAEeCDAa1tErEpvg"
        incoming="#_5w1tALF9EeCDAa1tErEpvg #_6WxroLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_J0aeULF-EeCDAa1tErEpvg"
          valueExpression="VISIBILITYPRIVAT" operator="UNEQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_NbnOgLF-EeCDAa1tErEpvg"
          additional="true" valueExpression="get.*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_qQEt0LF9EeCDAa1tErEpvg" name="returnTypeAccess"
        outgoing="#_43aKoLF9EeCDAa1tErEpvg" incoming="#_6KSHwLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_qkAWcLF9EeCDAa1tErEpvg" name="fieldType"
        incoming="#_43aKoLF9EeCDAa1tErEpvg #_5Q2lYLF9EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/types#//GASTType"/>
    <nodes xsi:type="specification:PSObject" id="_q00ZoLF9EeCDAa1tErEpvg" name="var"
        outgoing="#_Fyq1QLGAEeCDAa1tErEpvg" incoming="#_F6cEwLxCEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_ERJqELF-EeCDAa1tErEpvg"
          additional="true" valueExpression="false" operator="EQUAL" attribute="http://www.fzi.de/gast/accesses#//VariableAccess/write"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_rQfpALF9EeCDAa1tErEpvg" name="field"
        outgoing="#_5Q2lYLF9EeCDAa1tErEpvg" incoming="#_59MG8LF9EeCDAa1tErEpvg #_qmmskLF_EeCDAa1tErEpvg #_Fyq1QLGAEeCDAa1tErEpvg"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_kO91ELF_EeCDAa1tErEpvg" name="param"
        modifier="NEGATIVE" incoming="#_loX4ALF_EeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_A3HhQLGAEeCDAa1tErEpvg" name="return"
        outgoing="#_F6cEwLxCEeCmFO4k1VVXaw" incoming="#_EurBoLGAEeCDAa1tErEpvg" instanceOf="http://www.fzi.de/gast/statements#//JumpStatement">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_Cp0N8LGAEeCDAa1tErEpvg"
          valueExpression="RETURN" operator="EQUAL" attribute="http://www.fzi.de/gast/statements#//JumpStatement/kind"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_ixwVsLw_EeCmFO4k1VVXaw" name="SelfGetMethod" superPattern="#_neldoLF9EeCDAa1tErEpvg">
    <connections xsi:type="specification:PSLink" id="_faYUwLxAEeCmFO4k1VVXaw" name="annotatedElement"
        source="#_ixwVsbw_EeCmFO4k1VVXaw" target="#_Ol3OsLxAEeCmFO4k1VVXaw" qualifier="getMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_fxWDQLxAEeCmFO4k1VVXaw" name="annotatedElement"
        source="#_ixwVsbw_EeCmFO4k1VVXaw" target="#_O7wmMLxAEeCmFO4k1VVXaw" qualifier="returnedField"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_gnvfwLxAEeCmFO4k1VVXaw" name="link3"
        source="#_Ol3OsLxAEeCmFO4k1VVXaw" target="#_PUBVMLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_g46IQLxAEeCmFO4k1VVXaw" name="link4"
        source="#_Ol3OsLxAEeCmFO4k1VVXaw" target="#_Piq1MLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_hQaCQLxAEeCmFO4k1VVXaw" name="link5"
        source="#_Piq1MLxAEeCmFO4k1VVXaw" target="#_P-5eMLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_iEqwwLxAEeCmFO4k1VVXaw" name="link6"
        source="#_O7wmMLxAEeCmFO4k1VVXaw" target="#_P-5eMLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_jabCwLxAEeCmFO4k1VVXaw" name="link7"
        source="#_P-5eMLxAEeCmFO4k1VVXaw" target="#_O7wmMLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_kNAWQLxAEeCmFO4k1VVXaw" name="link8"
        source="#_P-5eMLxAEeCmFO4k1VVXaw" target="#_Ol3OsLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_qPgrQLxAEeCmFO4k1VVXaw" name="link9"
        source="#_QuWlMLxAEeCmFO4k1VVXaw" target="#_O7wmMLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSPath" id="_q71lwLxAEeCmFO4k1VVXaw" name="path1"
        source="#_Ol3OsLxAEeCmFO4k1VVXaw" target="#_QUabMLxAEeCmFO4k1VVXaw"/>
    <connections xsi:type="specification:PSPath" id="_rQb8wLxAEeCmFO4k1VVXaw" name="path2"
        source="#_QUabMLxAEeCmFO4k1VVXaw" target="#_QuWlMLxAEeCmFO4k1VVXaw"/>
    <nodes xsi:type="specification:PSAnnotation" id="_ixwVsbw_EeCmFO4k1VVXaw" outgoing="#_faYUwLxAEeCmFO4k1VVXaw #_fxWDQLxAEeCmFO4k1VVXaw"
        type="#_ixwVsLw_EeCmFO4k1VVXaw"/>
    <nodes xsi:type="specification:PSObject" id="_Ol3OsLxAEeCmFO4k1VVXaw" name="getter"
        outgoing="#_gnvfwLxAEeCmFO4k1VVXaw #_g46IQLxAEeCmFO4k1VVXaw #_q71lwLxAEeCmFO4k1VVXaw"
        incoming="#_faYUwLxAEeCmFO4k1VVXaw #_kNAWQLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_umAxcLxAEeCmFO4k1VVXaw"
          additional="true" valueExpression="get.*" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_vTzEgLxAEeCmFO4k1VVXaw"
          valueExpression="VISIBILITYPRIVAT" operator="UNEQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_O7wmMLxAEeCmFO4k1VVXaw" name="field"
        outgoing="#_iEqwwLxAEeCmFO4k1VVXaw" incoming="#_fxWDQLxAEeCmFO4k1VVXaw #_jabCwLxAEeCmFO4k1VVXaw #_qPgrQLxAEeCmFO4k1VVXaw"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_PUBVMLxAEeCmFO4k1VVXaw" name="param"
        modifier="NEGATIVE" incoming="#_gnvfwLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_Piq1MLxAEeCmFO4k1VVXaw" name="returnTypeAccess"
        outgoing="#_hQaCQLxAEeCmFO4k1VVXaw" incoming="#_g46IQLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_P-5eMLxAEeCmFO4k1VVXaw" name="singletonType"
        outgoing="#_jabCwLxAEeCmFO4k1VVXaw #_kNAWQLxAEeCmFO4k1VVXaw" incoming="#_hQaCQLxAEeCmFO4k1VVXaw #_iEqwwLxAEeCmFO4k1VVXaw"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_QUabMLxAEeCmFO4k1VVXaw" name="return"
        outgoing="#_rQb8wLxAEeCmFO4k1VVXaw" incoming="#_q71lwLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/statements#//JumpStatement"/>
    <nodes xsi:type="specification:PSObject" id="_QuWlMLxAEeCmFO4k1VVXaw" name="fieldAccess"
        outgoing="#_qPgrQLxAEeCmFO4k1VVXaw" incoming="#_rQb8wLxAEeCmFO4k1VVXaw" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess"/>
  </patternSpecifications>
  <patternSpecifications id="_K6LIsMyqEeCDk6rZEciVDQ" name="ContainerType2">
    <connections xsi:type="specification:PSLink" id="_W_0r4MyqEeCDk6rZEciVDQ" name="annotatedElement"
        source="#_K6RPUMyqEeCDk6rZEciVDQ" target="#_OcagYMyqEeCDk6rZEciVDQ" qualifier="containerType"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_K6RPUMyqEeCDk6rZEciVDQ" outgoing="#_W_0r4MyqEeCDk6rZEciVDQ"
        type="#_K6LIsMyqEeCDk6rZEciVDQ"/>
    <nodes xsi:type="specification:PSObject" id="_OcagYMyqEeCDk6rZEciVDQ" name="type"
        incoming="#_W_0r4MyqEeCDk6rZEciVDQ" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_TN88wMyqEeCDk6rZEciVDQ"
          valueExpression=".*(Collection|Map|List)" operator="REGULAR_EXPRESSION"
          attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_xBYjENUrEeCOSvCxQ0HiYA" name="Observer1">
    <connections xsi:type="specification:PSLink" id="_NUo9UNUsEeCOSvCxQ0HiYA" name="link1"
        source="#_5luisNUrEeCOSvCxQ0HiYA" target="#_6PYjsNUrEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_NihoUNUsEeCOSvCxQ0HiYA" name="link2"
        source="#_5luisNUrEeCOSvCxQ0HiYA" target="#_6jJ0QNUrEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_NwkEUNUsEeCOSvCxQ0HiYA" name="link3"
        source="#_5SlkQNUrEeCOSvCxQ0HiYA" target="#_62KP0NUrEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_N91EQNUsEeCOSvCxQ0HiYA" name="link4"
        source="#_6PYjsNUrEeCOSvCxQ0HiYA" target="#_LhMpwNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_OHIxQNUsEeCOSvCxQ0HiYA" name="link5"
        source="#_LhMpwNUsEeCOSvCxQ0HiYA" target="#_5SlkQNUrEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_OX7mUNUsEeCOSvCxQ0HiYA" name="link6"
        source="#_7S_VwNUrEeCOSvCxQ0HiYA" target="#_5luisNUrEeCOSvCxQ0HiYA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Oi-_wNUsEeCOSvCxQ0HiYA" name="link7"
        source="#_7S_VwNUrEeCOSvCxQ0HiYA" target="#_5SlkQNUrEeCOSvCxQ0HiYA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_PBp4wNUsEeCOSvCxQ0HiYA" name="link8"
        source="#_7-oTwNUrEeCOSvCxQ0HiYA" target="#_6jJ0QNUrEeCOSvCxQ0HiYA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_PpacUNUsEeCOSvCxQ0HiYA" name="link9"
        source="#_7-oTwNUrEeCOSvCxQ0HiYA" target="#_7pzTQNUrEeCOSvCxQ0HiYA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Qml2QNUsEeCOSvCxQ0HiYA" name="link10"
        source="#_8UcywNUrEeCOSvCxQ0HiYA" target="#_7pzTQNUrEeCOSvCxQ0HiYA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_XhfL0NUsEeCOSvCxQ0HiYA" name="link11"
        source="#_8UcywNUrEeCOSvCxQ0HiYA" target="#_62KP0NUrEeCOSvCxQ0HiYA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_ZkUmQNUsEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xBa_UNUrEeCOSvCxQ0HiYA" target="#_5SlkQNUrEeCOSvCxQ0HiYA" qualifier="observerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Zu760NUsEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xBa_UNUrEeCOSvCxQ0HiYA" target="#_5luisNUrEeCOSvCxQ0HiYA" qualifier="subjectClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Z6_Z0NUsEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xBa_UNUrEeCOSvCxQ0HiYA" target="#_62KP0NUrEeCOSvCxQ0HiYA" qualifier="updateMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_aO070NUsEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xBa_UNUrEeCOSvCxQ0HiYA" target="#_6PYjsNUrEeCOSvCxQ0HiYA" qualifier="registerMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_abjwQNUsEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xBa_UNUrEeCOSvCxQ0HiYA" target="#_6jJ0QNUrEeCOSvCxQ0HiYA" qualifier="notifyMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_tguDQNUsEeCOSvCxQ0HiYA" name="link17"
        source="#_r9TIsNUsEeCOSvCxQ0HiYA" target="#_62KP0NUrEeCOSvCxQ0HiYA" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_xBa_UNUrEeCOSvCxQ0HiYA" outgoing="#_ZkUmQNUsEeCOSvCxQ0HiYA #_Zu760NUsEeCOSvCxQ0HiYA #_Z6_Z0NUsEeCOSvCxQ0HiYA #_aO070NUsEeCOSvCxQ0HiYA #_abjwQNUsEeCOSvCxQ0HiYA"
        type="#_xBYjENUrEeCOSvCxQ0HiYA"/>
    <nodes xsi:type="specification:PSObject" id="_5SlkQNUrEeCOSvCxQ0HiYA" name="observerClass"
        outgoing="#_NwkEUNUsEeCOSvCxQ0HiYA" incoming="#_OHIxQNUsEeCOSvCxQ0HiYA #_Oi-_wNUsEeCOSvCxQ0HiYA #_ZkUmQNUsEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_SVaIgNUsEeCOSvCxQ0HiYA"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_SkvlANUsEeCOSvCxQ0HiYA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//GASTClass/interface"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_5luisNUrEeCOSvCxQ0HiYA" name="subjectClass"
        outgoing="#_NUo9UNUsEeCOSvCxQ0HiYA #_NihoUNUsEeCOSvCxQ0HiYA" incoming="#_OX7mUNUsEeCOSvCxQ0HiYA #_Zu760NUsEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_SBbckNUsEeCOSvCxQ0HiYA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_6PYjsNUrEeCOSvCxQ0HiYA" name="register"
        outgoing="#_N91EQNUsEeCOSvCxQ0HiYA" incoming="#_NUo9UNUsEeCOSvCxQ0HiYA #_aO070NUsEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_6jJ0QNUrEeCOSvCxQ0HiYA" name="notify"
        incoming="#_NihoUNUsEeCOSvCxQ0HiYA #_PBp4wNUsEeCOSvCxQ0HiYA #_abjwQNUsEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_62KP0NUrEeCOSvCxQ0HiYA" name="update"
        incoming="#_NwkEUNUsEeCOSvCxQ0HiYA #_XhfL0NUsEeCOSvCxQ0HiYA #_Z6_Z0NUsEeCOSvCxQ0HiYA #_tguDQNUsEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_7S_VwNUrEeCOSvCxQ0HiYA" name="anno2"
        outgoing="#_OX7mUNUsEeCOSvCxQ0HiYA #_Oi-_wNUsEeCOSvCxQ0HiYA" type="#_emQtULEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSObject" id="_7pzTQNUrEeCOSvCxQ0HiYA" name="m"
        incoming="#_PpacUNUsEeCOSvCxQ0HiYA #_Qml2QNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_7-oTwNUrEeCOSvCxQ0HiYA" name="anno3"
        outgoing="#_PBp4wNUsEeCOSvCxQ0HiYA #_PpacUNUsEeCOSvCxQ0HiYA" type="#_3qb0MK0qEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_8UcywNUrEeCOSvCxQ0HiYA" name="anno4"
        outgoing="#_Qml2QNUsEeCOSvCxQ0HiYA #_XhfL0NUsEeCOSvCxQ0HiYA" type="#_zz1goK0rEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSObject" id="_LhMpwNUsEeCOSvCxQ0HiYA" name="p"
        outgoing="#_OHIxQNUsEeCOSvCxQ0HiYA" incoming="#_N91EQNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSAnnotation" id="_r9TIsNUsEeCOSvCxQ0HiYA" name="anno5"
        parents="#_ubAhQNUsEeCOSvCxQ0HiYA" outgoing="#_tguDQNUsEeCOSvCxQ0HiYA" type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <combinedFragments id="_ubAhQNUsEeCOSvCxQ0HiYA" name="frag1" children="#_r9TIsNUsEeCOSvCxQ0HiYA"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_xmA6INUrEeCOSvCxQ0HiYA" name="Observer2">
    <connections xsi:type="specification:PSLink" id="_7NpGQNUsEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xmA6IdUrEeCOSvCxQ0HiYA" target="#_3P9vwNUsEeCOSvCxQ0HiYA" qualifier="subjectClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_FxEowNUtEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xmA6IdUrEeCOSvCxQ0HiYA" target="#_3eYmQNUsEeCOSvCxQ0HiYA" qualifier="registerMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_F9bpwNUtEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xmA6IdUrEeCOSvCxQ0HiYA" target="#_3tfZQNUsEeCOSvCxQ0HiYA" qualifier="notifyMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_GNdp0NUtEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xmA6IdUrEeCOSvCxQ0HiYA" target="#_4SQTMNUsEeCOSvCxQ0HiYA" qualifier="updateMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_GaNFUNUtEeCOSvCxQ0HiYA" name="annotatedElement"
        source="#_xmA6IdUrEeCOSvCxQ0HiYA" target="#_29WVwNUsEeCOSvCxQ0HiYA" qualifier="observerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_KcTBwNUtEeCOSvCxQ0HiYA" name="link6"
        source="#_3P9vwNUsEeCOSvCxQ0HiYA" target="#_3eYmQNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_Kr3HwNUtEeCOSvCxQ0HiYA" name="link7"
        source="#_3P9vwNUsEeCOSvCxQ0HiYA" target="#_3tfZQNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_K9ylQNUtEeCOSvCxQ0HiYA" name="link8"
        source="#_3eYmQNUsEeCOSvCxQ0HiYA" target="#_39qjQNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_LHodwNUtEeCOSvCxQ0HiYA" name="link9"
        source="#_39qjQNUsEeCOSvCxQ0HiYA" target="#_29WVwNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/variables#//Variable/type"/>
    <connections xsi:type="specification:PSLink" id="_LTUJUNUtEeCOSvCxQ0HiYA" name="link10"
        source="#_29WVwNUsEeCOSvCxQ0HiYA" target="#_4SQTMNUsEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_WlzxQNUtEeCOSvCxQ0HiYA" name="link11"
        source="#_PVVNQNUtEeCOSvCxQ0HiYA" target="#_4SQTMNUsEeCOSvCxQ0HiYA" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_W9AJQNUtEeCOSvCxQ0HiYA" name="link12"
        source="#_OlV6wNUtEeCOSvCxQ0HiYA" target="#_3tfZQNUsEeCOSvCxQ0HiYA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_XIJCQNUtEeCOSvCxQ0HiYA" name="link13"
        source="#_OlV6wNUtEeCOSvCxQ0HiYA" target="#_5EAgQNUsEeCOSvCxQ0HiYA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_XSScwNUtEeCOSvCxQ0HiYA" name="link14"
        source="#_O-NtwNUtEeCOSvCxQ0HiYA" target="#_5EAgQNUsEeCOSvCxQ0HiYA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_XdHzwNUtEeCOSvCxQ0HiYA" name="link15"
        source="#_O-NtwNUtEeCOSvCxQ0HiYA" target="#_4SQTMNUsEeCOSvCxQ0HiYA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_eCpWQNUtEeCOSvCxQ0HiYA" name="link16"
        source="#_N6PIQNUtEeCOSvCxQ0HiYA" target="#_3P9vwNUsEeCOSvCxQ0HiYA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_eRwJQNUtEeCOSvCxQ0HiYA" name="link17"
        source="#_N6PIQNUtEeCOSvCxQ0HiYA" target="#_29WVwNUsEeCOSvCxQ0HiYA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_xmA6IdUrEeCOSvCxQ0HiYA" outgoing="#_7NpGQNUsEeCOSvCxQ0HiYA #_FxEowNUtEeCOSvCxQ0HiYA #_F9bpwNUtEeCOSvCxQ0HiYA #_GNdp0NUtEeCOSvCxQ0HiYA #_GaNFUNUtEeCOSvCxQ0HiYA"
        type="#_xmA6INUrEeCOSvCxQ0HiYA"/>
    <nodes xsi:type="specification:PSObject" id="_29WVwNUsEeCOSvCxQ0HiYA" name="observerClass"
        outgoing="#_LTUJUNUtEeCOSvCxQ0HiYA" incoming="#_GaNFUNUtEeCOSvCxQ0HiYA #_LHodwNUtEeCOSvCxQ0HiYA #_eRwJQNUtEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_25tcwNX5EeC4_4Y9pe3SQw"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//GASTClass/interface"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_3M5egNX5EeC4_4Y9pe3SQw"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_3P9vwNUsEeCOSvCxQ0HiYA" name="subjectClass"
        outgoing="#_KcTBwNUtEeCOSvCxQ0HiYA #_Kr3HwNUtEeCOSvCxQ0HiYA" incoming="#_7NpGQNUsEeCOSvCxQ0HiYA #_eCpWQNUtEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_7FNM0NX5EeC4_4Y9pe3SQw"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_3eYmQNUsEeCOSvCxQ0HiYA" name="register"
        outgoing="#_K9ylQNUtEeCOSvCxQ0HiYA" incoming="#_FxEowNUtEeCOSvCxQ0HiYA #_KcTBwNUtEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_3tfZQNUsEeCOSvCxQ0HiYA" name="notify"
        incoming="#_F9bpwNUtEeCOSvCxQ0HiYA #_Kr3HwNUtEeCOSvCxQ0HiYA #_W9AJQNUtEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_39qjQNUsEeCOSvCxQ0HiYA" name="p"
        outgoing="#_LHodwNUtEeCOSvCxQ0HiYA" incoming="#_K9ylQNUtEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_4SQTMNUsEeCOSvCxQ0HiYA" name="update"
        incoming="#_GNdp0NUtEeCOSvCxQ0HiYA #_LTUJUNUtEeCOSvCxQ0HiYA #_WlzxQNUtEeCOSvCxQ0HiYA #_XdHzwNUtEeCOSvCxQ0HiYA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_5EAgQNUsEeCOSvCxQ0HiYA" name="m"
        incoming="#_XIJCQNUtEeCOSvCxQ0HiYA #_XSScwNUtEeCOSvCxQ0HiYA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_N6PIQNUtEeCOSvCxQ0HiYA" name="anno2"
        outgoing="#_eCpWQNUtEeCOSvCxQ0HiYA #_eRwJQNUtEeCOSvCxQ0HiYA" type="#_emQtULEVEeCUkfYLwzc8Zg"/>
    <nodes xsi:type="specification:PSAnnotation" id="_OlV6wNUtEeCOSvCxQ0HiYA" name="anno3"
        outgoing="#_W9AJQNUtEeCOSvCxQ0HiYA #_XIJCQNUtEeCOSvCxQ0HiYA" type="#_zz1goK0rEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_O-NtwNUtEeCOSvCxQ0HiYA" name="anno4"
        outgoing="#_XSScwNUtEeCOSvCxQ0HiYA #_XdHzwNUtEeCOSvCxQ0HiYA" type="#_3qb0MK0qEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_PVVNQNUtEeCOSvCxQ0HiYA" name="anno5"
        parents="#_aHVXQNUtEeCOSvCxQ0HiYA" outgoing="#_WlzxQNUtEeCOSvCxQ0HiYA" type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <combinedFragments id="_aHVXQNUtEeCOSvCxQ0HiYA" name="frag1" children="#_PVVNQNUtEeCOSvCxQ0HiYA"
        kind="SET"/>
  </patternSpecifications>
</specification:PSCatalog>
