<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_ovwPsK1NEeCtn6bi_IANnw"
    name="Blog Test Catalog" metamodel="de.fzi.gast">
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
        outgoing="#_IjBNAK1OEeCtn6bi_IANnw #_J3uWIK1OEeCtn6bi_IANnw" incoming="#_H-j1EK1OEeCtn6bi_IANnw"
        instanceOf="http://www.fzi.de/gast/functions#//Method">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_2ZVDwK1NEeCtn6bi_IANnw"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/static"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_mHS4UK1QEeCtn6bi_IANnw"
          additional="true" valueExpression="get*" operator="REGULAR_EXPRESSION" attribute="http://www.fzi.de/gast/core#//NamedModelElement/simpleName"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_JQy5AK1OEeCtn6bi_IANnw" name="fieldAccess"
        outgoing="#_KHqPkK1OEeCtn6bi_IANnw" incoming="#_J3uWIK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/accesses#//VariableAccess"/>
    <nodes xsi:type="specification:PSObject" id="_vCyUAK1NEeCtn6bi_IANnw" name="publicConstructor"
        modifier="NEGATIVE" incoming="#_HRsvEK1OEeCtn6bi_IANnw" instanceOf="http://www.fzi.de/gast/functions#//Constructor">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_1q7I0K1REeCtn6bi_IANnw"
          valueExpression="VISIBILITYPUBLIC" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_Su1I4K8_EeCF_8JYN4-2IA" name="Inheritance">
    <connections xsi:type="specification:PSLink" id="_ZAU8IK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Su1v8K8_EeCF_8JYN4-2IA" target="#_TsCYAK8_EeCF_8JYN4-2IA" qualifier="sup"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_ZKjPIK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_Su1v8K8_EeCF_8JYN4-2IA" target="#_XQsKgK8_EeCF_8JYN4-2IA" qualifier="sub"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_bp60kK8_EeCF_8JYN4-2IA" name="link3"
        source="#_XQsKgK8_EeCF_8JYN4-2IA" target="#_TsCYAK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Su1v8K8_EeCF_8JYN4-2IA" outgoing="#_ZAU8IK8_EeCF_8JYN4-2IA #_ZKjPIK8_EeCF_8JYN4-2IA"
        type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_TsCYAK8_EeCF_8JYN4-2IA" name="superClass"
        incoming="#_ZAU8IK8_EeCF_8JYN4-2IA #_bp60kK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_XQsKgK8_EeCF_8JYN4-2IA" name="subClass"
        outgoing="#_bp60kK8_EeCF_8JYN4-2IA" incoming="#_ZKjPIK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_gKPlIK8_EeCF_8JYN4-2IA" name="Observer">
    <connections xsi:type="specification:PSLink" id="_ozEcIK8_EeCF_8JYN4-2IA" name="link1"
        source="#_mAYSEK8_EeCF_8JYN4-2IA" target="#_nPo6gK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Function/formalParameters"/>
    <connections xsi:type="specification:PSLink" id="_pPwYIK8_EeCF_8JYN4-2IA" name="link2"
        source="#_kNFvgK8_EeCF_8JYN4-2IA" target="#_mAYSEK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_ppUHoK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_kNFvgK8_EeCF_8JYN4-2IA" qualifier="subject"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_p_wRsK8_EeCF_8JYN4-2IA" name="annotatedElement"
        source="#_gKPlIa8_EeCF_8JYN4-2IA" target="#_haHRkK8_EeCF_8JYN4-2IA" qualifier="observer"
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
    <nodes xsi:type="specification:PSAnnotation" id="_gKPlIa8_EeCF_8JYN4-2IA" outgoing="#_ppUHoK8_EeCF_8JYN4-2IA #_p_wRsK8_EeCF_8JYN4-2IA"
        type="#_gKPlIK8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_haHRkK8_EeCF_8JYN4-2IA" name="obj1"
        outgoing="#_xM3YoK8_EeCF_8JYN4-2IA" incoming="#_p_wRsK8_EeCF_8JYN4-2IA #_qXFzoK8_EeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_1HvSwK8_EeCF_8JYN4-2IA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_3pVs0K8_EeCF_8JYN4-2IA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//GASTClass/interface"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_kNFvgK8_EeCF_8JYN4-2IA" name="obj2"
        outgoing="#_pPwYIK8_EeCF_8JYN4-2IA #_w864IK8_EeCF_8JYN4-2IA" incoming="#_ppUHoK8_EeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_7-DqUK8_EeCF_8JYN4-2IA"
          additional="true" valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/abstract"/>
    </nodes>
    <nodes xsi:type="specification:PSObject" id="_mAYSEK8_EeCF_8JYN4-2IA" name="obj3"
        outgoing="#_ozEcIK8_EeCF_8JYN4-2IA" incoming="#_pPwYIK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_nPo6gK8_EeCF_8JYN4-2IA" name="obj4"
        outgoing="#_qXFzoK8_EeCF_8JYN4-2IA" incoming="#_ozEcIK8_EeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//FormalParameter"/>
    <nodes xsi:type="specification:PSObject" id="_udExkK8_EeCF_8JYN4-2IA" name="obj5"
        incoming="#_xM3YoK8_EeCF_8JYN4-2IA #_j9ka4K9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_vpwAEK8_EeCF_8JYN4-2IA" name="obj6"
        incoming="#_w864IK8_EeCF_8JYN4-2IA #_kN-OYK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_zOGQkK8_EeCF_8JYN4-2IA" name="anno2"
        outgoing="#_j9ka4K9BEeCF_8JYN4-2IA #_kN-OYK9BEeCF_8JYN4-2IA" type="#_CBRJ8K9AEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_CBRJ8K9AEeCF_8JYN4-2IA" name="Delegation" superPattern="#_E3RGsK9AEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_G179YK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_BzRUwK9BEeCF_8JYN4-2IA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HBXxUK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_CDStwK9BEeCF_8JYN4-2IA" qualifier="callerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HY8j0K9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_CQVrQK9BEeCF_8JYN4-2IA" qualifier="calleeClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_HtAvUK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_CBRJ8a9AEeCF_8JYN4-2IA" target="#_Ca7xsK9BEeCF_8JYN4-2IA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Kb4wYK9BEeCF_8JYN4-2IA" name="link5"
        source="#_Jg0PQK9BEeCF_8JYN4-2IA" target="#_CQVrQK9BEeCF_8JYN4-2IA" qualifier="referencingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_KoBH4K9BEeCF_8JYN4-2IA" name="link6"
        source="#_Jg0PQK9BEeCF_8JYN4-2IA" target="#_IhpRQK9BEeCF_8JYN4-2IA" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_LUfMUK9BEeCF_8JYN4-2IA" name="link7"
        source="#_CDStwK9BEeCF_8JYN4-2IA" target="#_IhpRQK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_MrATUK9BEeCF_8JYN4-2IA" name="link8"
        source="#_CDStwK9BEeCF_8JYN4-2IA" target="#_BzRUwK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_Np4WYK9BEeCF_8JYN4-2IA" name="link9"
        source="#_CQVrQK9BEeCF_8JYN4-2IA" target="#_Ca7xsK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <nodes xsi:type="specification:PSAnnotation" id="_CBRJ8a9AEeCF_8JYN4-2IA" outgoing="#_G179YK9BEeCF_8JYN4-2IA #_HBXxUK9BEeCF_8JYN4-2IA #_HY8j0K9BEeCF_8JYN4-2IA #_HtAvUK9BEeCF_8JYN4-2IA"
        type="#_CBRJ8K9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_BzRUwK9BEeCF_8JYN4-2IA" name="obj1"
        incoming="#_G179YK9BEeCF_8JYN4-2IA #_MrATUK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_CDStwK9BEeCF_8JYN4-2IA" name="obj2"
        outgoing="#_LUfMUK9BEeCF_8JYN4-2IA #_MrATUK9BEeCF_8JYN4-2IA" incoming="#_HBXxUK9BEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_CQVrQK9BEeCF_8JYN4-2IA" name="obj3"
        outgoing="#_Np4WYK9BEeCF_8JYN4-2IA" incoming="#_HY8j0K9BEeCF_8JYN4-2IA #_Kb4wYK9BEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_Ca7xsK9BEeCF_8JYN4-2IA" name="obj4"
        incoming="#_HtAvUK9BEeCF_8JYN4-2IA #_Np4WYK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_IhpRQK9BEeCF_8JYN4-2IA" name="obj5"
        incoming="#_KoBH4K9BEeCF_8JYN4-2IA #_LUfMUK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Jg0PQK9BEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_Kb4wYK9BEeCF_8JYN4-2IA #_KoBH4K9BEeCF_8JYN4-2IA" type="#_Xl_PoK9AEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_E3RGsK9AEeCF_8JYN4-2IA" name="NeighborCall" subPatterns="#_CBRJ8K9AEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_P9a7wK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_E3RGsa9AEeCF_8JYN4-2IA" target="#_I6H4IK9AEeCF_8JYN4-2IA" qualifier="caller"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_QIjNsK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_E3RGsa9AEeCF_8JYN4-2IA" target="#_IDkqoK9AEeCF_8JYN4-2IA" qualifier="callerClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_QU19QK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_E3RGsa9AEeCF_8JYN4-2IA" target="#_JvTMoK9AEeCF_8JYN4-2IA" qualifier="calleeClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_QmJvsK9AEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_E3RGsa9AEeCF_8JYN4-2IA" target="#_KtyOIK9AEeCF_8JYN4-2IA" qualifier="callee"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_UDQvwK9AEeCF_8JYN4-2IA" name="link5"
        source="#_JvTMoK9AEeCF_8JYN4-2IA" target="#_KtyOIK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_UfpJwK9AEeCF_8JYN4-2IA" name="link6"
        source="#_IDkqoK9AEeCF_8JYN4-2IA" target="#_I6H4IK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_WBNqMK9AEeCF_8JYN4-2IA" name="link7"
        source="#_IDkqoK9AEeCF_8JYN4-2IA" target="#_RMwcsK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <connections xsi:type="specification:PSLink" id="_8y2H0K9AEeCF_8JYN4-2IA" name="link8"
        source="#_SdY-IK9AEeCF_8JYN4-2IA" target="#_RMwcsK9AEeCF_8JYN4-2IA" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_9YKbYK9AEeCF_8JYN4-2IA" name="link9"
        source="#_SdY-IK9AEeCF_8JYN4-2IA" target="#_JvTMoK9AEeCF_8JYN4-2IA" qualifier="references"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_E3RGsa9AEeCF_8JYN4-2IA" outgoing="#_P9a7wK9AEeCF_8JYN4-2IA #_QIjNsK9AEeCF_8JYN4-2IA #_QU19QK9AEeCF_8JYN4-2IA #_QmJvsK9AEeCF_8JYN4-2IA"
        type="#_E3RGsK9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_IDkqoK9AEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_UfpJwK9AEeCF_8JYN4-2IA #_WBNqMK9AEeCF_8JYN4-2IA" incoming="#_QIjNsK9AEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_I6H4IK9AEeCF_8JYN4-2IA" name="obj2"
        incoming="#_P9a7wK9AEeCF_8JYN4-2IA #_UfpJwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_JvTMoK9AEeCF_8JYN4-2IA" name="obj3"
        outgoing="#_UDQvwK9AEeCF_8JYN4-2IA" incoming="#_QU19QK9AEeCF_8JYN4-2IA #_9YKbYK9AEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_KtyOIK9AEeCF_8JYN4-2IA" name="obj4"
        incoming="#_QmJvsK9AEeCF_8JYN4-2IA #_UDQvwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_RMwcsK9AEeCF_8JYN4-2IA" name="obj5"
        incoming="#_WBNqMK9AEeCF_8JYN4-2IA #_8y2H0K9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSAnnotation" id="_SdY-IK9AEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_8y2H0K9AEeCF_8JYN4-2IA #_9YKbYK9AEeCF_8JYN4-2IA" type="#_Xl_PoK9AEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_Xl_PoK9AEeCF_8JYN4-2IA" name="Reference" subPatterns="#_nkALgK9AEeCF_8JYN4-2IA"
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
    <nodes xsi:type="specification:PSObject" id="_ZSLrsK9AEeCF_8JYN4-2IA" name="obj1"
        incoming="#_bC9FwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_ZbG-MK9AEeCF_8JYN4-2IA" name="obj2"
        incoming="#_bPnowK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_ZnZGsK9AEeCF_8JYN4-2IA" name="obj3"
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
    <nodes xsi:type="specification:PSAnnotation" id="_nkALga9AEeCF_8JYN4-2IA" outgoing="#_xvOCUK9AEeCF_8JYN4-2IA #_x9kAUK9AEeCF_8JYN4-2IA #_y2PUwK9AEeCF_8JYN4-2IA"
        type="#_nkALgK9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_uCxlwK9AEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_1QvoUK9AEeCF_8JYN4-2IA" incoming="#_xvOCUK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_uStfMK9AEeCF_8JYN4-2IA" name="obj2"
        outgoing="#_1bpQwK9AEeCF_8JYN4-2IA" incoming="#_x9kAUK9AEeCF_8JYN4-2IA #_1QvoUK9AEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_uePZwK9AEeCF_8JYN4-2IA" name="obj3"
        incoming="#_y2PUwK9AEeCF_8JYN4-2IA #_1bpQwK9AEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_vXaoAK9BEeCF_8JYN4-2IA" name="OverridingMethod">
    <connections xsi:type="specification:PSLink" id="_yspecK9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_vXaoAa9BEeCF_8JYN4-2IA" target="#_wz2w0K9BEeCF_8JYN4-2IA" qualifier="overriding"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_y_fh8K9BEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_vXaoAa9BEeCF_8JYN4-2IA" target="#_wmC-UK9BEeCF_8JYN4-2IA" qualifier="overriddenMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_3BmFcK9BEeCF_8JYN4-2IA" name="link3"
        source="#_wmC-UK9BEeCF_8JYN4-2IA" target="#_zs3lUK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_3QjugK9BEeCF_8JYN4-2IA" name="link4"
        source="#_zs3lUK9BEeCF_8JYN4-2IA" target="#_0JRNcK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_4JsV8K9BEeCF_8JYN4-2IA" name="link5"
        source="#_wz2w0K9BEeCF_8JYN4-2IA" target="#_0bCS4K9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_4aBQ8K9BEeCF_8JYN4-2IA" name="link6"
        source="#_0bCS4K9BEeCF_8JYN4-2IA" target="#_0JRNcK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_-QUV8K9BEeCF_8JYN4-2IA" name="link7"
        source="#_wmC-UK9BEeCF_8JYN4-2IA" target="#_82DXYK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method/surroundingClass"/>
    <connections xsi:type="specification:PSLink" id="_AWcU8K9CEeCF_8JYN4-2IA" name="link8"
        source="#_wz2w0K9BEeCF_8JYN4-2IA" target="#_9HEO4K9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method/surroundingClass"/>
    <connections xsi:type="specification:PSLink" id="_ClMrgK9CEeCF_8JYN4-2IA" name="link9"
        source="#_76oQ8K9BEeCF_8JYN4-2IA" target="#_82DXYK9BEeCF_8JYN4-2IA" qualifier="sub"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_C4MgAK9CEeCF_8JYN4-2IA" name="link10"
        source="#_76oQ8K9BEeCF_8JYN4-2IA" target="#_9HEO4K9BEeCF_8JYN4-2IA" qualifier="sup"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_vXaoAa9BEeCF_8JYN4-2IA" outgoing="#_yspecK9BEeCF_8JYN4-2IA #_y_fh8K9BEeCF_8JYN4-2IA"
        type="#_vXaoAK9BEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_wmC-UK9BEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_3BmFcK9BEeCF_8JYN4-2IA #_-QUV8K9BEeCF_8JYN4-2IA" incoming="#_y_fh8K9BEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_wz2w0K9BEeCF_8JYN4-2IA" name="obj2"
        outgoing="#_4JsV8K9BEeCF_8JYN4-2IA #_AWcU8K9CEeCF_8JYN4-2IA" incoming="#_yspecK9BEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_zs3lUK9BEeCF_8JYN4-2IA" name="obj3"
        outgoing="#_3QjugK9BEeCF_8JYN4-2IA" incoming="#_3BmFcK9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_0JRNcK9BEeCF_8JYN4-2IA" name="obj4"
        incoming="#_3QjugK9BEeCF_8JYN4-2IA #_4aBQ8K9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_0bCS4K9BEeCF_8JYN4-2IA" name="obj5"
        outgoing="#_4aBQ8K9BEeCF_8JYN4-2IA" incoming="#_4JsV8K9BEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSAnnotation" id="_76oQ8K9BEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_ClMrgK9CEeCF_8JYN4-2IA #_C4MgAK9CEeCF_8JYN4-2IA" type="#_Su1I4K8_EeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_82DXYK9BEeCF_8JYN4-2IA" name="obj6"
        incoming="#_-QUV8K9BEeCF_8JYN4-2IA #_ClMrgK9CEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_9HEO4K9BEeCF_8JYN4-2IA" name="obj7"
        incoming="#_AWcU8K9CEeCF_8JYN4-2IA #_C4MgAK9CEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_qxJ8oK9DEeCF_8JYN4-2IA" name="Strategy">
    <connections xsi:type="specification:PSLink" id="_vVEzwK9DEeCF_8JYN4-2IA" name="link1"
        source="#_t8U4IK9DEeCF_8JYN4-2IA" target="#_sjI3oK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <connections xsi:type="specification:PSLink" id="_wf1WsK9DEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_qxJ8oa9DEeCF_8JYN4-2IA" target="#_t8U4IK9DEeCF_8JYN4-2IA" qualifier="context"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_wvPrsK9DEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_qxJ8oa9DEeCF_8JYN4-2IA" target="#_sjI3oK9DEeCF_8JYN4-2IA" qualifier="strategy"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_1506MK9DEeCF_8JYN4-2IA" name="link4"
        source="#_zu5TsK9DEeCF_8JYN4-2IA" target="#_0mqpMK9DEeCF_8JYN4-2IA" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_2pmKQK9DEeCF_8JYN4-2IA" name="link5"
        source="#_t8U4IK9DEeCF_8JYN4-2IA" target="#_0mqpMK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
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
    <nodes xsi:type="specification:PSAnnotation" id="_qxJ8oa9DEeCF_8JYN4-2IA" outgoing="#_wf1WsK9DEeCF_8JYN4-2IA #_wvPrsK9DEeCF_8JYN4-2IA"
        type="#_qxJ8oK9DEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_sjI3oK9DEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_9W5vwK9DEeCF_8JYN4-2IA" incoming="#_vVEzwK9DEeCF_8JYN4-2IA #_wvPrsK9DEeCF_8JYN4-2IA #_31vNQK9DEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_t8U4IK9DEeCF_8JYN4-2IA" name="obj2"
        outgoing="#_vVEzwK9DEeCF_8JYN4-2IA #_2pmKQK9DEeCF_8JYN4-2IA #_928hwK9DEeCF_8JYN4-2IA"
        incoming="#_wf1WsK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_zu5TsK9DEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_1506MK9DEeCF_8JYN4-2IA #_31vNQK9DEeCF_8JYN4-2IA" type="#_Xl_PoK9AEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_0mqpMK9DEeCF_8JYN4-2IA" name="obj3"
        incoming="#_1506MK9DEeCF_8JYN4-2IA #_2pmKQK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_6Sb5MK9DEeCF_8JYN4-2IA" name="obj4"
        incoming="#_9W5vwK9DEeCF_8JYN4-2IA #__R9uQK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_7khzMK9DEeCF_8JYN4-2IA" name="obj5"
        incoming="#_928hwK9DEeCF_8JYN4-2IA #__dQYQK9DEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSAnnotation" id="_-VTRsK9DEeCF_8JYN4-2IA" name="anno3"
        outgoing="#__R9uQK9DEeCF_8JYN4-2IA #__dQYQK9DEeCF_8JYN4-2IA" type="#_E3RGsK9AEeCF_8JYN4-2IA"/>
  </patternSpecifications>
</specification:PSCatalog>
