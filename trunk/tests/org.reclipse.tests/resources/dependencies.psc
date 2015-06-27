<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_ZzfFIK9EEeCF_8JYN4-2IA"
    name="Dependency Test Catalog" metamodel="de.fzi.gast">
  <patternSpecifications id="_ZzfFIa9EEeCF_8JYN4-2IA" name="SimpleA" subPatterns="#_oPFzEK9EEeCF_8JYN4-2IA #_0jCQ8K9EEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_hFhIUK9EEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_ZzfFIq9EEeCF_8JYN4-2IA" target="#_ebrYQK9EEeCF_8JYN4-2IA" qualifier="pack"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_ZzfFIq9EEeCF_8JYN4-2IA" outgoing="#_hFhIUK9EEeCF_8JYN4-2IA"
        type="#_ZzfFIa9EEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_ebrYQK9EEeCF_8JYN4-2IA" name="obj1"
        incoming="#_hFhIUK9EEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/core#//Package"/>
  </patternSpecifications>
  <patternSpecifications id="_oPFzEK9EEeCF_8JYN4-2IA" name="SimpleB" superPattern="#_ZzfFIa9EEeCF_8JYN4-2IA"
      subPatterns="#_B6gBUK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_rgLwYK9EEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_oPFzEa9EEeCF_8JYN4-2IA" target="#_qefC0K9EEeCF_8JYN4-2IA" qualifier="pack"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_oPFzEa9EEeCF_8JYN4-2IA" outgoing="#_rgLwYK9EEeCF_8JYN4-2IA"
        type="#_oPFzEK9EEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_qefC0K9EEeCF_8JYN4-2IA" name="obj1"
        incoming="#_rgLwYK9EEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/core#//Package">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_vfthAK9EEeCF_8JYN4-2IA"
          valueExpression="NORMAL" operator="EQUAL" attribute="http://www.fzi.de/gast/core#//ModelElement/status"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_0jCQ8K9EEeCF_8JYN4-2IA" name="SimpleC" superPattern="#_ZzfFIa9EEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_4Z_UYK9EEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_0jCQ8a9EEeCF_8JYN4-2IA" target="#_1ZQuUK9EEeCF_8JYN4-2IA" qualifier="pack"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_6wht8K9EEeCF_8JYN4-2IA" name="link2"
        source="#_1ZQuUK9EEeCF_8JYN4-2IA" target="#_5tEF0K9EEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/core#//Package/classes"/>
    <nodes xsi:type="specification:PSAnnotation" id="_0jCQ8a9EEeCF_8JYN4-2IA" outgoing="#_4Z_UYK9EEeCF_8JYN4-2IA"
        type="#_0jCQ8K9EEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_1ZQuUK9EEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_6wht8K9EEeCF_8JYN4-2IA" incoming="#_4Z_UYK9EEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/core#//Package"/>
    <nodes xsi:type="specification:PSObject" id="_5tEF0K9EEeCF_8JYN4-2IA" name="obj2"
        incoming="#_6wht8K9EEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_9kmYEK9EEeCF_8JYN4-2IA"
          valueExpression="VISIBILITYPRIVAT" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//Member/visibility"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_B6gBUK9FEeCF_8JYN4-2IA" name="SimpleD" superPattern="#_oPFzEK9EEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_EKef4K9FEeCF_8JYN4-2IA" name="link1"
        source="#_CobeUK9FEeCF_8JYN4-2IA" target="#_DWbz0K9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/core#//Package/classes"/>
    <connections xsi:type="specification:PSLink" id="_EXN7YK9FEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_B6gBUa9FEeCF_8JYN4-2IA" target="#_CobeUK9FEeCF_8JYN4-2IA" qualifier="pack"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_B6gBUa9FEeCF_8JYN4-2IA" outgoing="#_EXN7YK9FEeCF_8JYN4-2IA"
        type="#_B6gBUK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_CobeUK9FEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_EKef4K9FEeCF_8JYN4-2IA" incoming="#_EXN7YK9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/core#//Package"/>
    <nodes xsi:type="specification:PSObject" id="_DWbz0K9FEeCF_8JYN4-2IA" name="obj2"
        incoming="#_EKef4K9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_H4PLEK9FEeCF_8JYN4-2IA"
          valueExpression="true" operator="EQUAL" attribute="http://www.fzi.de/gast/types#//GASTClass/interface"/>
    </nodes>
  </patternSpecifications>
  <patternSpecifications id="_LoyEgK9FEeCF_8JYN4-2IA" name="RefA" subPatterns="#_MvWD8K9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_WpkRYK9FEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_LoyEga9FEeCF_8JYN4-2IA" target="#_VfttYK9FEeCF_8JYN4-2IA" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_LoyEga9FEeCF_8JYN4-2IA" outgoing="#_WpkRYK9FEeCF_8JYN4-2IA"
        type="#_LoyEgK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_VfttYK9FEeCF_8JYN4-2IA" name="obj1"
        incoming="#_WpkRYK9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_MLTiwK9FEeCF_8JYN4-2IA" name="RefB" subPatterns="#_NOznIK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_hhxKAK9FEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_MLUJ0K9FEeCF_8JYN4-2IA" target="#_guU64K9FEeCF_8JYN4-2IA" qualifier="interface"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_l2txAK9FEeCF_8JYN4-2IA" name="link2"
        source="#_i53lYK9FEeCF_8JYN4-2IA" target="#_guU64K9FEeCF_8JYN4-2IA" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_MLUJ0K9FEeCF_8JYN4-2IA" outgoing="#_hhxKAK9FEeCF_8JYN4-2IA"
        type="#_MLTiwK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_guU64K9FEeCF_8JYN4-2IA" name="obj1"
        incoming="#_hhxKAK9FEeCF_8JYN4-2IA #_l2txAK9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_i53lYK9FEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_l2txAK9FEeCF_8JYN4-2IA" type="#_LoyEgK9FEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_MvWD8K9FEeCF_8JYN4-2IA" name="RefC" superPattern="#_LoyEgK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_uCoCgK9FEeCF_8JYN4-2IA" name="link1"
        source="#_rgGbYK9FEeCF_8JYN4-2IA" target="#_ru6TcK9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/constructors"/>
    <connections xsi:type="specification:PSLink" id="_vgiyAK9FEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_MvWD8a9FEeCF_8JYN4-2IA" target="#_ru6TcK9FEeCF_8JYN4-2IA" qualifier="constructor"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_vzTV8K9FEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_MvWD8a9FEeCF_8JYN4-2IA" target="#_rgGbYK9FEeCF_8JYN4-2IA" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_MvWD8a9FEeCF_8JYN4-2IA" outgoing="#_vgiyAK9FEeCF_8JYN4-2IA #_vzTV8K9FEeCF_8JYN4-2IA"
        type="#_MvWD8K9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_rgGbYK9FEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_uCoCgK9FEeCF_8JYN4-2IA" incoming="#_vzTV8K9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_ru6TcK9FEeCF_8JYN4-2IA" name="obj2"
        incoming="#_uCoCgK9FEeCF_8JYN4-2IA #_vgiyAK9FEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Constructor"/>
  </patternSpecifications>
  <patternSpecifications id="_NOznIK9FEeCF_8JYN4-2IA" name="RefD" superPattern="#_MLTiwK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_Ev4dkK9GEeCF_8JYN4-2IA" name="link1"
        source="#_DWOjAK9GEeCF_8JYN4-2IA" target="#_CFf68K9GEeCF_8JYN4-2IA" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_FMfhEK9GEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_NO0OMK9FEeCF_8JYN4-2IA" target="#_CFf68K9GEeCF_8JYN4-2IA" qualifier="type"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_FaTTkK9GEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_NO0OMK9FEeCF_8JYN4-2IA" target="#__E7s8K9FEeCF_8JYN4-2IA" qualifier="constructor"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_MeknkK9GEeCF_8JYN4-2IA" name="link4"
        source="#__E7s8K9FEeCF_8JYN4-2IA" target="#_LkWbAK9GEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_M6knEK9GEeCF_8JYN4-2IA" name="link5"
        source="#_LkWbAK9GEeCF_8JYN4-2IA" target="#_CFf68K9GEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <nodes xsi:type="specification:PSAnnotation" id="_NO0OMK9FEeCF_8JYN4-2IA" outgoing="#_FMfhEK9GEeCF_8JYN4-2IA #_FaTTkK9GEeCF_8JYN4-2IA"
        type="#_NOznIK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="__E7s8K9FEeCF_8JYN4-2IA" name="obj1"
        outgoing="#_MeknkK9GEeCF_8JYN4-2IA" incoming="#_FaTTkK9GEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/functions#//Constructor"/>
    <nodes xsi:type="specification:PSObject" id="_CFf68K9GEeCF_8JYN4-2IA" name="obj2"
        incoming="#_Ev4dkK9GEeCF_8JYN4-2IA #_FMfhEK9GEeCF_8JYN4-2IA #_M6knEK9GEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_DWOjAK9GEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_Ev4dkK9GEeCF_8JYN4-2IA" type="#_LoyEgK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_LkWbAK9GEeCF_8JYN4-2IA" name="obj3"
        outgoing="#_M6knEK9GEeCF_8JYN4-2IA" incoming="#_MeknkK9GEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
  </patternSpecifications>
  <patternSpecifications id="_N-Yp8K9FEeCF_8JYN4-2IA" name="RefE">
    <connections xsi:type="specification:PSLink" id="_WXDIkK9GEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_N-Yp8a9FEeCF_8JYN4-2IA" target="#_S8r6gK9GEeCF_8JYN4-2IA" qualifier="field"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_WrpfkK9GEeCF_8JYN4-2IA" name="annotatedElement"
        source="#_N-Yp8a9FEeCF_8JYN4-2IA" target="#_Ttg6gK9GEeCF_8JYN4-2IA" qualifier="daClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_XfYpoK9GEeCF_8JYN4-2IA" name="link3"
        source="#_UtrXAK9GEeCF_8JYN4-2IA" target="#_Ttg6gK9GEeCF_8JYN4-2IA" qualifier="type"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_X6AwIK9GEeCF_8JYN4-2IA" name="link4"
        source="#_Ttg6gK9GEeCF_8JYN4-2IA" target="#_S8r6gK9GEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/fields"/>
    <nodes xsi:type="specification:PSAnnotation" id="_N-Yp8a9FEeCF_8JYN4-2IA" outgoing="#_WXDIkK9GEeCF_8JYN4-2IA #_WrpfkK9GEeCF_8JYN4-2IA"
        type="#_N-Yp8K9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_S8r6gK9GEeCF_8JYN4-2IA" name="obj1"
        incoming="#_WXDIkK9GEeCF_8JYN4-2IA #_X6AwIK9GEeCF_8JYN4-2IA" instanceOf="http://www.fzi.de/gast/variables#//Field"/>
    <nodes xsi:type="specification:PSObject" id="_Ttg6gK9GEeCF_8JYN4-2IA" name="obj2"
        outgoing="#_X6AwIK9GEeCF_8JYN4-2IA" incoming="#_WrpfkK9GEeCF_8JYN4-2IA #_XfYpoK9GEeCF_8JYN4-2IA"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_UtrXAK9GEeCF_8JYN4-2IA" name="anno2"
        outgoing="#_XfYpoK9GEeCF_8JYN4-2IA" type="#_NOznIK9FEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_OtxfgK9FEeCF_8JYN4-2IA" name="CircA" subPatterns="#_PV52gK9FEeCF_8JYN4-2IA #_RrSZgK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_4TuM8K9HEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_Otxfga9FEeCF_8JYN4-2IA" target="#_iOnzgK9GEeCF_8JYN4-2IA" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Otxfga9FEeCF_8JYN4-2IA" outgoing="#_4TuM8K9HEeCKeeTFCHcyGg"
        type="#_OtxfgK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_iOnzgK9GEeCF_8JYN4-2IA" name="obj1"
        incoming="#_4TuM8K9HEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_PV52gK9FEeCF_8JYN4-2IA" name="CircB" superPattern="#_OtxfgK9FEeCF_8JYN4-2IA"
      subPatterns="#_SZgKYK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_JqUsYK9IEeCKeeTFCHcyGg" name="link1"
        source="#_H1MWwK9IEeCKeeTFCHcyGg" target="#_IG5x0K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/surroundingPackage"/>
    <connections xsi:type="specification:PSLink" id="_KCWx4K9IEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_PV52ga9FEeCF_8JYN4-2IA" target="#_H1MWwK9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_KS1d4K9IEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_PV52ga9FEeCF_8JYN4-2IA" target="#_IG5x0K9IEeCKeeTFCHcyGg" qualifier="package"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_PV52ga9FEeCF_8JYN4-2IA" outgoing="#_KCWx4K9IEeCKeeTFCHcyGg #_KS1d4K9IEeCKeeTFCHcyGg"
        type="#_PV52gK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_H1MWwK9IEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_JqUsYK9IEeCKeeTFCHcyGg" incoming="#_KCWx4K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_IG5x0K9IEeCKeeTFCHcyGg" name="obj2"
        incoming="#_JqUsYK9IEeCKeeTFCHcyGg #_KS1d4K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/core#//Package"/>
  </patternSpecifications>
  <patternSpecifications id="_RrSZgK9FEeCF_8JYN4-2IA" name="CircC" superPattern="#_OtxfgK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_W_Np4K9IEeCKeeTFCHcyGg" name="link1"
        source="#_Tf0K0K9IEeCKeeTFCHcyGg" target="#_TMvd0K9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_XYKVYK9IEeCKeeTFCHcyGg" name="link2"
        source="#_TMvd0K9IEeCKeeTFCHcyGg" target="#_So4i0K9IEeCKeeTFCHcyGg" negative="true"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass/surroundingPackage"/>
    <connections xsi:type="specification:PSLink" id="_X0VUAK9IEeCKeeTFCHcyGg" name="link3"
        source="#_RznuwK9IEeCKeeTFCHcyGg" target="#_So4i0K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/surroundingPackage"/>
    <connections xsi:type="specification:PSLink" id="_aHkh8K9IEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_RrSZga9FEeCF_8JYN4-2IA" target="#_So4i0K9IEeCKeeTFCHcyGg" qualifier="package"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_a3LZ8K9IEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_RrSZga9FEeCF_8JYN4-2IA" target="#_RznuwK9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_dW8A8K9IEeCKeeTFCHcyGg" name="link6"
        source="#_RznuwK9IEeCKeeTFCHcyGg" target="#_TMvd0K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/allAccessedClasses"/>
    <nodes xsi:type="specification:PSAnnotation" id="_RrSZga9FEeCF_8JYN4-2IA" outgoing="#_aHkh8K9IEeCKeeTFCHcyGg #_a3LZ8K9IEeCKeeTFCHcyGg"
        type="#_RrSZgK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_RznuwK9IEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_X0VUAK9IEeCKeeTFCHcyGg #_dW8A8K9IEeCKeeTFCHcyGg" incoming="#_a3LZ8K9IEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_So4i0K9IEeCKeeTFCHcyGg" name="obj2"
        incoming="#_XYKVYK9IEeCKeeTFCHcyGg #_X0VUAK9IEeCKeeTFCHcyGg #_aHkh8K9IEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/core#//Package"/>
    <nodes xsi:type="specification:PSObject" id="_TMvd0K9IEeCKeeTFCHcyGg" name="obj3"
        outgoing="#_XYKVYK9IEeCKeeTFCHcyGg" incoming="#_W_Np4K9IEeCKeeTFCHcyGg #_dW8A8K9IEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Tf0K0K9IEeCKeeTFCHcyGg" name="anno2"
        outgoing="#_W_Np4K9IEeCKeeTFCHcyGg" type="#_OtxfgK9FEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_SZgKYK9FEeCF_8JYN4-2IA" name="CircD" superPattern="#_PV52gK9FEeCF_8JYN4-2IA"
      subPatterns="#_TPU_IK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_oyNxcK9IEeCKeeTFCHcyGg" name="link1"
        source="#_mSltUK9IEeCKeeTFCHcyGg" target="#_l7eN0K9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_rMBhcK9IEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_SZgKYa9FEeCF_8JYN4-2IA" target="#_lMj5YK9IEeCKeeTFCHcyGg" qualifier="method"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_sRc4cK9IEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_SZgKYa9FEeCF_8JYN4-2IA" target="#_lgZbYK9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_s1O68K9IEeCKeeTFCHcyGg" name="link4"
        source="#_lgZbYK9IEeCKeeTFCHcyGg" target="#_lMj5YK9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_tMbS8K9IEeCKeeTFCHcyGg" name="link5"
        source="#_l7eN0K9IEeCKeeTFCHcyGg" target="#_lgZbYK9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <connections xsi:type="specification:PSLink" id="_0YyzAK9IEeCKeeTFCHcyGg" name="link6"
        source="#_lMj5YK9IEeCKeeTFCHcyGg" target="#_l7eN0K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//Member/overriddenMember"/>
    <nodes xsi:type="specification:PSAnnotation" id="_SZgKYa9FEeCF_8JYN4-2IA" outgoing="#_rMBhcK9IEeCKeeTFCHcyGg #_sRc4cK9IEeCKeeTFCHcyGg"
        type="#_SZgKYK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_lMj5YK9IEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_0YyzAK9IEeCKeeTFCHcyGg" incoming="#_rMBhcK9IEeCKeeTFCHcyGg #_s1O68K9IEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_lgZbYK9IEeCKeeTFCHcyGg" name="obj2"
        outgoing="#_s1O68K9IEeCKeeTFCHcyGg" incoming="#_sRc4cK9IEeCKeeTFCHcyGg #_tMbS8K9IEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_l7eN0K9IEeCKeeTFCHcyGg" name="obj3"
        outgoing="#_tMbS8K9IEeCKeeTFCHcyGg" incoming="#_oyNxcK9IEeCKeeTFCHcyGg #_0YyzAK9IEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_mSltUK9IEeCKeeTFCHcyGg" name="anno2"
        outgoing="#_oyNxcK9IEeCKeeTFCHcyGg" type="#_RrSZgK9FEeCF_8JYN4-2IA"/>
  </patternSpecifications>
  <patternSpecifications id="_TPU_IK9FEeCF_8JYN4-2IA" name="CircE" superPattern="#_SZgKYK9FEeCF_8JYN4-2IA">
    <connections xsi:type="specification:PSLink" id="_---ukK9IEeCKeeTFCHcyGg" name="link1"
        source="#_-BADYK9IEeCKeeTFCHcyGg" target="#_7P2KYK9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="__VejAK9IEeCKeeTFCHcyGg" name="link2"
        source="#_8dXtcK9IEeCKeeTFCHcyGg" target="#_6XT_4K9IEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="__pPMgK9IEeCKeeTFCHcyGg" name="link3"
        source="#_7P2KYK9IEeCKeeTFCHcyGg" target="#_6XT_4K9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <connections xsi:type="specification:PSLink" id="_AGmd8K9JEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_TPVmMK9FEeCF_8JYN4-2IA" target="#_6XT_4K9IEeCKeeTFCHcyGg" qualifier="class2"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_TPVmMK9FEeCF_8JYN4-2IA" outgoing="#_AGmd8K9JEeCKeeTFCHcyGg"
        type="#_TPU_IK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSObject" id="_6XT_4K9IEeCKeeTFCHcyGg" name="obj1"
        incoming="#__VejAK9IEeCKeeTFCHcyGg #__pPMgK9IEeCKeeTFCHcyGg #_AGmd8K9JEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_7P2KYK9IEeCKeeTFCHcyGg" name="obj2"
        outgoing="#__pPMgK9IEeCKeeTFCHcyGg" incoming="#_---ukK9IEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSAnnotation" id="_8dXtcK9IEeCKeeTFCHcyGg" name="anno2"
        outgoing="#__VejAK9IEeCKeeTFCHcyGg" type="#_OtxfgK9FEeCF_8JYN4-2IA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_-BADYK9IEeCKeeTFCHcyGg" name="anno3"
        outgoing="#_---ukK9IEeCKeeTFCHcyGg" type="#_RrSZgK9FEeCF_8JYN4-2IA"/>
  </patternSpecifications>
</specification:PSCatalog>
