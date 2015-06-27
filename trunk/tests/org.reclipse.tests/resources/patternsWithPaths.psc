<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_3qbNIK0qEeC73qeN65GIrA"
    name="Patterns Catalog" metamodel="de.fzi.gast">
  <patternSpecifications id="_3qb0MK0qEeC73qeN65GIrA" name="Delegation">
    <connections xsi:type="specification:PSPath" id="_NZdFYK0rEeC73qeN65GIrA" name="path1"
        source="#_-ljzQK0qEeC73qeN65GIrA" target="#_Cv-lQK0rEeC73qeN65GIrA"/>
    <connections xsi:type="specification:PSLink" id="_OD1fIK0rEeC73qeN65GIrA" name="link1"
        source="#_Cv-lQK0rEeC73qeN65GIrA" target="#_IDfGQK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess/targetFunction"/>
    <connections xsi:type="specification:PSLink" id="_Q4wd0K0rEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_-ljzQK0qEeC73qeN65GIrA" qualifier="callingMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_RbLOYK0rEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_IDfGQK0rEeC73qeN65GIrA" qualifier="calledMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_DwITUK0uEeC73qeN65GIrA" name="link4"
        source="#_-ljzQK0qEeC73qeN65GIrA" target="#_98ReUK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_HVYiwK0uEeC73qeN65GIrA" name="link5"
        source="#_IDfGQK0rEeC73qeN65GIrA" target="#_EfCnwK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Function/returnTypeDeclaration"/>
    <connections xsi:type="specification:PSLink" id="_H_0m4K0uEeC73qeN65GIrA" name="link6"
        source="#_EfCnwK0uEeC73qeN65GIrA" target="#_5Ty3UK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_IMGvYK0uEeC73qeN65GIrA" name="link7"
        source="#_98ReUK0tEeC73qeN65GIrA" target="#_5Ty3UK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//Access/accessedTarget"/>
    <connections xsi:type="specification:PSLink" id="_MK110K0uEeC73qeN65GIrA" name="link8"
        source="#_I4VjQK0uEeC73qeN65GIrA" target="#_IDfGQK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_MbjLUK0uEeC73qeN65GIrA" name="link9"
        source="#_JFxiUK0uEeC73qeN65GIrA" target="#_-ljzQK0qEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_MnxCYK0uEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_JFxiUK0uEeC73qeN65GIrA" qualifier="callingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_M1ul4K0uEeC73qeN65GIrA" name="annotatedElement"
        source="#_3qb0Ma0qEeC73qeN65GIrA" target="#_I4VjQK0uEeC73qeN65GIrA" qualifier="calledClass"
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
    <nodes xsi:type="specification:PSAnnotation" id="_3qb0Ma0qEeC73qeN65GIrA" outgoing="#_Q4wd0K0rEeC73qeN65GIrA #_RbLOYK0rEeC73qeN65GIrA #_MnxCYK0uEeC73qeN65GIrA #_M1ul4K0uEeC73qeN65GIrA"
        type="#_3qb0MK0qEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSObject" id="_-ljzQK0qEeC73qeN65GIrA" name="caller"
        outgoing="#_NZdFYK0rEeC73qeN65GIrA #_DwITUK0uEeC73qeN65GIrA #_zlCvUK0uEeC73qeN65GIrA"
        incoming="#_Q4wd0K0rEeC73qeN65GIrA #_MbjLUK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_Cv-lQK0rEeC73qeN65GIrA" name="call"
        outgoing="#_OD1fIK0rEeC73qeN65GIrA" incoming="#_NZdFYK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <nodes xsi:type="specification:PSObject" id="_IDfGQK0rEeC73qeN65GIrA" name="callee"
        outgoing="#_HVYiwK0uEeC73qeN65GIrA #_HPlnUK0vEeC73qeN65GIrA" incoming="#_OD1fIK0rEeC73qeN65GIrA #_RbLOYK0rEeC73qeN65GIrA #_MK110K0uEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_5Ty3UK0tEeC73qeN65GIrA" name="returnType"
        incoming="#_H_0m4K0uEeC73qeN65GIrA #_IMGvYK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTType"/>
    <nodes xsi:type="specification:PSObject" id="_98ReUK0tEeC73qeN65GIrA" name="returnTypeAccessCaller"
        outgoing="#_IMGvYK0uEeC73qeN65GIrA" incoming="#_DwITUK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_EfCnwK0uEeC73qeN65GIrA" name="returnTypeAccessCallee"
        outgoing="#_H_0m4K0uEeC73qeN65GIrA" incoming="#_HVYiwK0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//DeclarationTypeAccess"/>
    <nodes xsi:type="specification:PSObject" id="_I4VjQK0uEeC73qeN65GIrA" name="calleeClass"
        outgoing="#_MK110K0uEeC73qeN65GIrA" incoming="#_M1ul4K0uEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
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
  <patternSpecifications id="_zz1goK0rEeC73qeN65GIrA" name="MultiNeighborCall">
    <connections xsi:type="specification:PSLink" id="_JyRG0K0sEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_BnCKwK0sEeC73qeN65GIrA" qualifier="callingMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_KA_fUK0sEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_-3e0QK0rEeC73qeN65GIrA" qualifier="calledMethod"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSPath" id="_KmFJYK0sEeC73qeN65GIrA" name="path1"
        source="#_BnCKwK0sEeC73qeN65GIrA" target="#_49rvUK0rEeC73qeN65GIrA"/>
    <connections xsi:type="specification:PSLink" id="_LfNw0K0sEeC73qeN65GIrA" name="link3"
        source="#_89aUQK0rEeC73qeN65GIrA" target="#_-3e0QK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess/targetFunction"/>
    <connections xsi:type="specification:PSPath" id="_4oxZ0K0sEeC73qeN65GIrA" name="path2"
        source="#_49rvUK0rEeC73qeN65GIrA" target="#_13ryQK0sEeC73qeN65GIrA"/>
    <connections xsi:type="specification:PSLink" id="_AVLE0K0tEeC73qeN65GIrA" name="link5"
        source="#_9pWXwK0sEeC73qeN65GIrA" target="#_-3e0QK0rEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_Eg0l4K0tEeC73qeN65GIrA" name="link6"
        source="#_DDHRwK0tEeC73qeN65GIrA" target="#_BnCKwK0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass/methods"/>
    <connections xsi:type="specification:PSLink" id="_Ip7wUK0tEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_9pWXwK0sEeC73qeN65GIrA" qualifier="calledClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_I0xuYK0tEeC73qeN65GIrA" name="annotatedElement"
        source="#_zz5LAK0rEeC73qeN65GIrA" target="#_DDHRwK0tEeC73qeN65GIrA" qualifier="callingClass"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSPath" id="_VSWCUK0tEeC73qeN65GIrA" name="path3"
        source="#_13ryQK0sEeC73qeN65GIrA" target="#_89aUQK0rEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSAnnotation" id="_zz5LAK0rEeC73qeN65GIrA" outgoing="#_JyRG0K0sEeC73qeN65GIrA #_KA_fUK0sEeC73qeN65GIrA #_Ip7wUK0tEeC73qeN65GIrA #_I0xuYK0tEeC73qeN65GIrA"
        type="#_zz1goK0rEeC73qeN65GIrA"/>
    <nodes xsi:type="specification:PSObject" id="_49rvUK0rEeC73qeN65GIrA" name="loop"
        outgoing="#_4oxZ0K0sEeC73qeN65GIrA" incoming="#_KmFJYK0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/statements#//LoopStatement"/>
    <nodes xsi:type="specification:PSObject" id="_89aUQK0rEeC73qeN65GIrA" name="call"
        outgoing="#_LfNw0K0sEeC73qeN65GIrA" incoming="#_VSWCUK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/accesses#//FunctionAccess"/>
    <nodes xsi:type="specification:PSObject" id="_-3e0QK0rEeC73qeN65GIrA" name="callee"
        incoming="#_KA_fUK0sEeC73qeN65GIrA #_LfNw0K0sEeC73qeN65GIrA #_AVLE0K0tEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_BnCKwK0sEeC73qeN65GIrA" name="caller"
        outgoing="#_KmFJYK0sEeC73qeN65GIrA" incoming="#_JyRG0K0sEeC73qeN65GIrA #_Eg0l4K0tEeC73qeN65GIrA"
        instanceOf="http://www.fzi.de/gast/functions#//Method"/>
    <nodes xsi:type="specification:PSObject" id="_13ryQK0sEeC73qeN65GIrA" name="block"
        outgoing="#_VSWCUK0tEeC73qeN65GIrA" incoming="#_4oxZ0K0sEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/statements#//BlockStatement"/>
    <nodes xsi:type="specification:PSObject" id="_9pWXwK0sEeC73qeN65GIrA" name="calleeClass"
        outgoing="#_AVLE0K0tEeC73qeN65GIrA" incoming="#_Ip7wUK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
    <nodes xsi:type="specification:PSObject" id="_DDHRwK0tEeC73qeN65GIrA" name="callerClass"
        outgoing="#_Eg0l4K0tEeC73qeN65GIrA" incoming="#_I0xuYK0tEeC73qeN65GIrA" instanceOf="http://www.fzi.de/gast/types#//GASTClass"/>
  </patternSpecifications>
</specification:PSCatalog>
