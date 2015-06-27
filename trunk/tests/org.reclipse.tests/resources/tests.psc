<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_QkAK8EXrEeGbTbm7F-6Yvg"
    name="Test Catalog" metamodel="org.reclipse.tests.metamodel">
  <patternSpecifications id="_QkAK8UXrEeGbTbm7F-6Yvg" name="DO">
    <connections xsi:type="specification:PSLink" id="_YPxj0EXrEeGbTbm7F-6Yvg" name="annotatedElement"
        source="#_QkAyAEXrEeGbTbm7F-6Yvg" target="#_WM2p0EXrEeGbTbm7F-6Yvg" qualifier="element1"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_QkAyAEXrEeGbTbm7F-6Yvg" outgoing="#_YPxj0EXrEeGbTbm7F-6Yvg"
        type="#_QkAK8UXrEeGbTbm7F-6Yvg"/>
    <nodes xsi:type="specification:PSObject" id="_WM2p0EXrEeGbTbm7F-6Yvg" name="obj1"
        incoming="#_YPxj0EXrEeGbTbm7F-6Yvg" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA"/>
  </patternSpecifications>
  <patternSpecifications id="_lx1aoEXrEeGbTbm7F-6Yvg" name="DO_DL_DO">
    <connections xsi:type="specification:PSLink" id="_nsPR0EXrEeGbTbm7F-6Yvg" name="link1"
        source="#_mSvIQEXrEeGbTbm7F-6Yvg" target="#_m9Pd0EXrEeGbTbm7F-6Yvg" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA/someAs"/>
    <connections xsi:type="specification:PSLink" id="_o0tVwEXrEeGbTbm7F-6Yvg" name="annotatedElement"
        source="#_lx1aoUXrEeGbTbm7F-6Yvg" target="#_mSvIQEXrEeGbTbm7F-6Yvg" qualifier="element1"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_lx1aoUXrEeGbTbm7F-6Yvg" outgoing="#_o0tVwEXrEeGbTbm7F-6Yvg"
        type="#_lx1aoEXrEeGbTbm7F-6Yvg"/>
    <nodes xsi:type="specification:PSObject" id="_mSvIQEXrEeGbTbm7F-6Yvg" name="obj1"
        outgoing="#_nsPR0EXrEeGbTbm7F-6Yvg" incoming="#_o0tVwEXrEeGbTbm7F-6Yvg" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA"/>
    <nodes xsi:type="specification:PSObject" id="_m9Pd0EXrEeGbTbm7F-6Yvg" name="obj2"
        incoming="#_nsPR0EXrEeGbTbm7F-6Yvg" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeB"/>
  </patternSpecifications>
  <patternSpecifications id="_q4XksEXtEeGoseG9owtGug" name="DO_DL_NO">
    <connections xsi:type="specification:PSLink" id="_w89kYEXtEeGoseG9owtGug" name="annotatedElement"
        source="#_q4YLwEXtEeGoseG9owtGug" target="#_ufomUEXtEeGoseG9owtGug" qualifier="element1"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_xH3M0EXtEeGoseG9owtGug" name="link2"
        source="#_ufomUEXtEeGoseG9owtGug" target="#_vllhwEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA/someAs"/>
    <nodes xsi:type="specification:PSAnnotation" id="_q4YLwEXtEeGoseG9owtGug" outgoing="#_w89kYEXtEeGoseG9owtGug"
        type="#_q4XksEXtEeGoseG9owtGug"/>
    <nodes xsi:type="specification:PSObject" id="_ufomUEXtEeGoseG9owtGug" name="obj1"
        outgoing="#_xH3M0EXtEeGoseG9owtGug" incoming="#_w89kYEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA"/>
    <nodes xsi:type="specification:PSObject" id="_vllhwEXtEeGoseG9owtGug" name="obj2"
        modifier="NEGATIVE" incoming="#_xH3M0EXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeB"/>
  </patternSpecifications>
  <patternSpecifications id="_1zLFYEXtEeGoseG9owtGug" name="DO_DL_AO">
    <connections xsi:type="specification:PSLink" id="_33_c0EXtEeGoseG9owtGug" name="annotatedElement"
        source="#_1zLFYUXtEeGoseG9owtGug" target="#_2TIX0EXtEeGoseG9owtGug" qualifier="element1"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_4BIxwEXtEeGoseG9owtGug" name="link2"
        source="#_2TIX0EXtEeGoseG9owtGug" target="#_2cquUEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA/someAs"/>
    <nodes xsi:type="specification:PSAnnotation" id="_1zLFYUXtEeGoseG9owtGug" outgoing="#_33_c0EXtEeGoseG9owtGug"
        type="#_1zLFYEXtEeGoseG9owtGug"/>
    <nodes xsi:type="specification:PSObject" id="_2TIX0EXtEeGoseG9owtGug" name="obj1"
        outgoing="#_4BIxwEXtEeGoseG9owtGug" incoming="#_33_c0EXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA"/>
    <nodes xsi:type="specification:PSObject" id="_2cquUEXtEeGoseG9owtGug" name="obj2"
        modifier="ADDITIONAL" incoming="#_4BIxwEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeB"/>
  </patternSpecifications>
  <patternSpecifications id="_6QHKwEXtEeGoseG9owtGug" name="DO_DL_SO">
    <connections xsi:type="specification:PSLink" id="_8EAxUEXtEeGoseG9owtGug" name="annotatedElement"
        source="#_6QHKwUXtEeGoseG9owtGug" target="#_7N_vUEXtEeGoseG9owtGug" qualifier="element1"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_9UapQEXtEeGoseG9owtGug" name="link2"
        source="#_7N_vUEXtEeGoseG9owtGug" target="#_7c8xUEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA/someAs"/>
    <nodes xsi:type="specification:PSAnnotation" id="_6QHKwUXtEeGoseG9owtGug" outgoing="#_8EAxUEXtEeGoseG9owtGug"
        type="#_6QHKwEXtEeGoseG9owtGug"/>
    <nodes xsi:type="specification:PSObject" id="_7N_vUEXtEeGoseG9owtGug" name="obj1"
        outgoing="#_9UapQEXtEeGoseG9owtGug" incoming="#_8EAxUEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA"/>
    <nodes xsi:type="specification:PSObject" id="_7c8xUEXtEeGoseG9owtGug" name="obj2"
        modifier="SET" incoming="#_9UapQEXtEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeB"/>
  </patternSpecifications>
  <patternSpecifications id="_Qnn_UEXuEeGoseG9owtGug" name="DO_NL_DO">
    <connections xsi:type="specification:PSLink" id="_S78LUEXuEeGoseG9owtGug" name="annotatedElement"
        source="#_Qnn_UUXuEeGoseG9owtGug" target="#_RRusQEXuEeGoseG9owtGug" qualifier="element1"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_UA_H0EXuEeGoseG9owtGug" name="link2"
        source="#_RRusQEXuEeGoseG9owtGug" target="#_RfT1QEXuEeGoseG9owtGug" negative="true"
        instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA/someAs"/>
    <nodes xsi:type="specification:PSAnnotation" id="_Qnn_UUXuEeGoseG9owtGug" outgoing="#_S78LUEXuEeGoseG9owtGug"
        type="#_Qnn_UEXuEeGoseG9owtGug"/>
    <nodes xsi:type="specification:PSObject" id="_RRusQEXuEeGoseG9owtGug" name="obj1"
        outgoing="#_UA_H0EXuEeGoseG9owtGug" incoming="#_S78LUEXuEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeA"/>
    <nodes xsi:type="specification:PSObject" id="_RfT1QEXuEeGoseG9owtGug" name="obj2"
        incoming="#_UA_H0EXuEeGoseG9owtGug" instanceOf="http://www.reclipse.org/ns/tests/2012#//TypeB"/>
  </patternSpecifications>
</specification:PSCatalog>
