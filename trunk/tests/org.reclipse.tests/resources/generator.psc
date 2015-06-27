<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_FrzkMK9PEeCKeeTFCHcyGg"
    name="Generator Test Catalog" metamodel="de.fzi.gast">
  <patternSpecifications id="_FrzkMa9PEeCKeeTFCHcyGg" name="Simple">
    <connections xsi:type="specification:PSLink" id="_JzSW8K9PEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_FrzkMq9PEeCKeeTFCHcyGg" target="#_H5SvcK9PEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_FrzkMq9PEeCKeeTFCHcyGg" outgoing="#_JzSW8K9PEeCKeeTFCHcyGg"
        type="#_FrzkMa9PEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_H5SvcK9PEeCKeeTFCHcyGg" name="obj1"
        incoming="#_JzSW8K9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
  <patternSpecifications id="_PfVTwK9PEeCKeeTFCHcyGg" name="SimpleAddFragment">
    <connections xsi:type="specification:PSLink" id="_VjLskK9PEeCKeeTFCHcyGg" name="link1"
        source="#_RW88sK9PEeCKeeTFCHcyGg" target="#_S95sYK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_VsjrAK9PEeCKeeTFCHcyGg" name="link2"
        source="#_S95sYK9PEeCKeeTFCHcyGg" target="#_UmCu4K9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_V4KeEK9PEeCKeeTFCHcyGg" name="link3"
        source="#_UmCu4K9PEeCKeeTFCHcyGg" target="#_RW88sK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <connections xsi:type="specification:PSLink" id="_cDTxsK9PEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_PfVTwa9PEeCKeeTFCHcyGg" target="#_RW88sK9PEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_PfVTwa9PEeCKeeTFCHcyGg" outgoing="#_cDTxsK9PEeCKeeTFCHcyGg"
        type="#_PfVTwK9PEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_RW88sK9PEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_VjLskK9PEeCKeeTFCHcyGg" incoming="#_V4KeEK9PEeCKeeTFCHcyGg #_cDTxsK9PEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_S95sYK9PEeCKeeTFCHcyGg" name="obj2"
        parents="#_dTfnMK9PEeCKeeTFCHcyGg" outgoing="#_VsjrAK9PEeCKeeTFCHcyGg" incoming="#_VjLskK9PEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_UmCu4K9PEeCKeeTFCHcyGg" name="obj3"
        parents="#_dTfnMK9PEeCKeeTFCHcyGg" outgoing="#_V4KeEK9PEeCKeeTFCHcyGg" incoming="#_VsjrAK9PEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <combinedFragments id="_dTfnMK9PEeCKeeTFCHcyGg" name="frag1" children="#_S95sYK9PEeCKeeTFCHcyGg #_UmCu4K9PEeCKeeTFCHcyGg"
        kind="ADDITIONAL"/>
  </patternSpecifications>
  <patternSpecifications id="_h8J0YK9PEeCKeeTFCHcyGg" name="SimpleNegFragment">
    <connections xsi:type="specification:PSLink" id="_lS01QK9PEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_h8J0Ya9PEeCKeeTFCHcyGg" target="#_jLJXEK9PEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_lgEnEK9PEeCKeeTFCHcyGg" name="link2"
        source="#_jLJXEK9PEeCKeeTFCHcyGg" target="#_j66AEK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <connections xsi:type="specification:PSLink" id="_lxB0MK9PEeCKeeTFCHcyGg" name="link3"
        source="#_j66AEK9PEeCKeeTFCHcyGg" target="#_jhUbYK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <nodes xsi:type="specification:PSAnnotation" id="_h8J0Ya9PEeCKeeTFCHcyGg" outgoing="#_lS01QK9PEeCKeeTFCHcyGg"
        type="#_h8J0YK9PEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_jLJXEK9PEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_lgEnEK9PEeCKeeTFCHcyGg" incoming="#_lS01QK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_jhUbYK9PEeCKeeTFCHcyGg" name="obj2"
        parents="#_kViGkK9PEeCKeeTFCHcyGg" incoming="#_lxB0MK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_j66AEK9PEeCKeeTFCHcyGg" name="obj3"
        parents="#_kViGkK9PEeCKeeTFCHcyGg" outgoing="#_lxB0MK9PEeCKeeTFCHcyGg" incoming="#_lgEnEK9PEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <combinedFragments id="_kViGkK9PEeCKeeTFCHcyGg" name="frag1" children="#_jhUbYK9PEeCKeeTFCHcyGg #_j66AEK9PEeCKeeTFCHcyGg"
        kind="NEGATIVE"/>
  </patternSpecifications>
  <patternSpecifications id="_xPh30K9PEeCKeeTFCHcyGg" name="SimpleSetFragment">
    <connections xsi:type="specification:PSLink" id="_3hgzQK9PEeCKeeTFCHcyGg" name="link1"
        source="#_x9piEK9PEeCKeeTFCHcyGg" target="#_yQF88K9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_8PXpgK9PEeCKeeTFCHcyGg" name="link2"
        source="#_ydVHsK9PEeCKeeTFCHcyGg" target="#_x9piEK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <connections xsi:type="specification:PSLink" id="_8gB7sK9PEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_xPh30a9PEeCKeeTFCHcyGg" target="#_ydVHsK9PEeCKeeTFCHcyGg" qualifier="class"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_xPh30a9PEeCKeeTFCHcyGg" outgoing="#_8gB7sK9PEeCKeeTFCHcyGg"
        type="#_xPh30K9PEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_x9piEK9PEeCKeeTFCHcyGg" name="obj1"
        parents="#_5dIycK9PEeCKeeTFCHcyGg" outgoing="#_3hgzQK9PEeCKeeTFCHcyGg" incoming="#_8PXpgK9PEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_yQF88K9PEeCKeeTFCHcyGg" name="obj2"
        parents="#_5dIycK9PEeCKeeTFCHcyGg" incoming="#_3hgzQK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_ydVHsK9PEeCKeeTFCHcyGg" name="obj3"
        outgoing="#_8PXpgK9PEeCKeeTFCHcyGg" incoming="#_8gB7sK9PEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <combinedFragments id="_5dIycK9PEeCKeeTFCHcyGg" name="frag1" children="#_x9piEK9PEeCKeeTFCHcyGg #_yQF88K9PEeCKeeTFCHcyGg"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_CF1toK9QEeCKeeTFCHcyGg" name="HierarchAddFragment">
    <connections xsi:type="specification:PSLink" id="_Et8_YK9QEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_CF1toa9QEeCKeeTFCHcyGg" target="#_Dp0B0K9QEeCKeeTFCHcyGg" qualifier="test"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_NmtIwK9QEeCKeeTFCHcyGg" name="link2"
        source="#_Dp0B0K9QEeCKeeTFCHcyGg" target="#_GJS8AK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_N9nz8K9QEeCKeeTFCHcyGg" name="link3"
        source="#_GJS8AK9QEeCKeeTFCHcyGg" target="#_Kg_VAK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_OQqrwK9QEeCKeeTFCHcyGg" name="link4"
        source="#_Kg_VAK9QEeCKeeTFCHcyGg" target="#_Dp0B0K9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/accesses#//TypeAccess/targetType"/>
    <nodes xsi:type="specification:PSAnnotation" id="_CF1toa9QEeCKeeTFCHcyGg" outgoing="#_Et8_YK9QEeCKeeTFCHcyGg"
        type="#_CF1toK9QEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_Dp0B0K9QEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_NmtIwK9QEeCKeeTFCHcyGg" incoming="#_Et8_YK9QEeCKeeTFCHcyGg #_OQqrwK9QEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_GJS8AK9QEeCKeeTFCHcyGg" name="obj2"
        parents="#_IwXr8K9QEeCKeeTFCHcyGg" outgoing="#_N9nz8K9QEeCKeeTFCHcyGg" incoming="#_NmtIwK9QEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_Kg_VAK9QEeCKeeTFCHcyGg" name="obj3"
        parents="#_MMQkAK9QEeCKeeTFCHcyGg" outgoing="#_OQqrwK9QEeCKeeTFCHcyGg" incoming="#_N9nz8K9QEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <combinedFragments id="_IwXr8K9QEeCKeeTFCHcyGg" name="frag1" children="#_GJS8AK9QEeCKeeTFCHcyGg #_MMQkAK9QEeCKeeTFCHcyGg"
        kind="ADDITIONAL"/>
    <combinedFragments id="_MMQkAK9QEeCKeeTFCHcyGg" name="frag2" parents="#_IwXr8K9QEeCKeeTFCHcyGg"
        children="#_Kg_VAK9QEeCKeeTFCHcyGg" kind="ADDITIONAL"/>
  </patternSpecifications>
  <patternSpecifications id="_TDuyIK9QEeCKeeTFCHcyGg" name="HierarchSetFragment">
    <connections xsi:type="specification:PSLink" id="_XueC8K9QEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_TDuyIa9QEeCKeeTFCHcyGg" target="#_VFBUcK9QEeCKeeTFCHcyGg" qualifier="toAnnotate0"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_X98CUK9QEeCKeeTFCHcyGg" name="link2"
        source="#_VFBUcK9QEeCKeeTFCHcyGg" target="#_UJ8zUK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/innerClasses"/>
    <connections xsi:type="specification:PSLink" id="_YLU-EK9QEeCKeeTFCHcyGg" name="link3"
        source="#_UJ8zUK9QEeCKeeTFCHcyGg" target="#_WSOucK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/superTypes"/>
    <nodes xsi:type="specification:PSAnnotation" id="_TDuyIa9QEeCKeeTFCHcyGg" outgoing="#_XueC8K9QEeCKeeTFCHcyGg"
        type="#_TDuyIK9QEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_UJ8zUK9QEeCKeeTFCHcyGg" name="obj1"
        parents="#_axg9MK9QEeCKeeTFCHcyGg" outgoing="#_YLU-EK9QEeCKeeTFCHcyGg" incoming="#_X98CUK9QEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_VFBUcK9QEeCKeeTFCHcyGg" name="obj2"
        outgoing="#_X98CUK9QEeCKeeTFCHcyGg" incoming="#_XueC8K9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_WSOucK9QEeCKeeTFCHcyGg" name="obj3"
        parents="#_enSU4K9QEeCKeeTFCHcyGg" incoming="#_YLU-EK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <combinedFragments id="_axg9MK9QEeCKeeTFCHcyGg" name="frag1" children="#_UJ8zUK9QEeCKeeTFCHcyGg #_enSU4K9QEeCKeeTFCHcyGg"
        kind="SET"/>
    <combinedFragments id="_enSU4K9QEeCKeeTFCHcyGg" name="frag2" parents="#_axg9MK9QEeCKeeTFCHcyGg"
        children="#_WSOucK9QEeCKeeTFCHcyGg" kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_miAvwK9QEeCKeeTFCHcyGg" name="MultiSetFragment">
    <connections xsi:type="specification:PSLink" id="_vhKcEK9QEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_miAvwa9QEeCKeeTFCHcyGg" target="#_nTP_cK9QEeCKeeTFCHcyGg" qualifier="pack"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_vz6Y8K9QEeCKeeTFCHcyGg" name="link2"
        source="#_nTP_cK9QEeCKeeTFCHcyGg" target="#_oKvoIK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/core#//Package/classes"/>
    <connections xsi:type="specification:PSLink" id="_wd3U4K9QEeCKeeTFCHcyGg" name="link3"
        source="#_oKvoIK9QEeCKeeTFCHcyGg" target="#_o0P4IK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <nodes xsi:type="specification:PSAnnotation" id="_miAvwa9QEeCKeeTFCHcyGg" outgoing="#_vhKcEK9QEeCKeeTFCHcyGg"
        type="#_miAvwK9QEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_nTP_cK9QEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_vz6Y8K9QEeCKeeTFCHcyGg" incoming="#_vhKcEK9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/core#//Package"/>
    <nodes xsi:type="specification:PSObject" id="_oKvoIK9QEeCKeeTFCHcyGg" name="obj2"
        parents="#_p2KBEK9QEeCKeeTFCHcyGg" outgoing="#_wd3U4K9QEeCKeeTFCHcyGg" incoming="#_vz6Y8K9QEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_o0P4IK9QEeCKeeTFCHcyGg" name="obj3"
        parents="#_sbcoUK9QEeCKeeTFCHcyGg" incoming="#_wd3U4K9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <combinedFragments id="_p2KBEK9QEeCKeeTFCHcyGg" name="frag1" children="#_oKvoIK9QEeCKeeTFCHcyGg"
        kind="SET"/>
    <combinedFragments id="_sbcoUK9QEeCKeeTFCHcyGg" name="frag2" children="#_o0P4IK9QEeCKeeTFCHcyGg"
        kind="SET"/>
  </patternSpecifications>
  <patternSpecifications id="_7nhw8K9QEeCKeeTFCHcyGg" name="OverlappingSetFragments">
    <connections xsi:type="specification:PSLink" id="_-MqnMK9QEeCKeeTFCHcyGg" name="annotatedElement"
        source="#_7nhw8a9QEeCKeeTFCHcyGg" target="#_9BsB0K9QEeCKeeTFCHcyGg" qualifier="test"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_CIsX4K9REeCKeeTFCHcyGg" name="link2"
        source="#__t-B4K9QEeCKeeTFCHcyGg" target="#_BWs6QK9REeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/functions#//Function/accesses"/>
    <connections xsi:type="specification:PSLink" id="_CeHOQK9REeCKeeTFCHcyGg" name="link3"
        source="#_Alm0gK9REeCKeeTFCHcyGg" target="#_BWs6QK9REeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/allAccesses"/>
    <connections xsi:type="specification:PSLink" id="_DCtwIK9REeCKeeTFCHcyGg" name="link4"
        source="#_9BsB0K9QEeCKeeTFCHcyGg" target="#__t-B4K9QEeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_DZB-YK9REeCKeeTFCHcyGg" name="link5"
        source="#_9BsB0K9QEeCKeeTFCHcyGg" target="#_Alm0gK9REeCKeeTFCHcyGg" instanceOf="http://www.fzi.de/gast/types#//GASTClass/innerClasses"/>
    <nodes xsi:type="specification:PSAnnotation" id="_7nhw8a9QEeCKeeTFCHcyGg" outgoing="#_-MqnMK9QEeCKeeTFCHcyGg"
        type="#_7nhw8K9QEeCKeeTFCHcyGg"/>
    <nodes xsi:type="specification:PSObject" id="_9BsB0K9QEeCKeeTFCHcyGg" name="obj1"
        outgoing="#_DCtwIK9REeCKeeTFCHcyGg #_DZB-YK9REeCKeeTFCHcyGg" incoming="#_-MqnMK9QEeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="__t-B4K9QEeCKeeTFCHcyGg" name="obj2"
        parents="#_I3r_cK9REeCKeeTFCHcyGg" outgoing="#_CIsX4K9REeCKeeTFCHcyGg" incoming="#_DCtwIK9REeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_Alm0gK9REeCKeeTFCHcyGg" name="obj3"
        parents="#_EiYMEK9REeCKeeTFCHcyGg" outgoing="#_CeHOQK9REeCKeeTFCHcyGg" incoming="#_DZB-YK9REeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_BWs6QK9REeCKeeTFCHcyGg" name="obj4"
        parents="#_EiYMEK9REeCKeeTFCHcyGg #_I3r_cK9REeCKeeTFCHcyGg" incoming="#_CIsX4K9REeCKeeTFCHcyGg #_CeHOQK9REeCKeeTFCHcyGg"
        instanceOf="http://www.fzi.de/gast/accesses#//Access"/>
    <combinedFragments id="_EiYMEK9REeCKeeTFCHcyGg" name="frag1" children="#_Alm0gK9REeCKeeTFCHcyGg #_BWs6QK9REeCKeeTFCHcyGg"
        kind="SET"/>
    <combinedFragments id="_I3r_cK9REeCKeeTFCHcyGg" name="frag2" children="#__t-B4K9QEeCKeeTFCHcyGg #_BWs6QK9REeCKeeTFCHcyGg"
        kind="SET"/>
  </patternSpecifications>
</specification:PSCatalog>
