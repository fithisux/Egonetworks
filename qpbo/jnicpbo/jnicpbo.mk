##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=jnicpbo
ConfigurationName      :=Debug
WorkspacePath          := "C:\workspace\Egonetworks\qpbo"
ProjectPath            := "C:\workspace\Egonetworks\qpbo\jnicpbo"
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=orbit
Date                   :=04/03/2016
CodeLitePath           :="C:\winoss\CodeLite"
LinkerName             :=C:/langs/tdm_x64/bin/g++.exe
SharedObjectLinkerName :=C:/langs/tdm_x64/bin/g++.exe -shared -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.o.i
DebugSwitch            :=-gstab
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputFile             :=$(IntermediateDirectory)/lib$(ProjectName).dll
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E 
ObjectsFileList        :="jnicpbo.txt"
PCHCompileFlags        :=
MakeDirCommand         :=makedir
RcCmpOptions           := 
RcCompilerName         :=C:/langs/tdm_x64/bin/windres.exe
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). $(IncludeSwitch)C:/langs/jdk8/include $(IncludeSwitch)C:/langs/jdk8/include/win32 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overriden using an environment variables
##
AR       := C:/langs/tdm_x64/bin/ar.exe rcus
CXX      := C:/langs/tdm_x64/bin/g++.exe
CC       := C:/langs/tdm_x64/bin/gcc.exe
CXXFLAGS :=  -g $(Preprocessors)
CFLAGS   :=  -g $(Preprocessors)
ASFLAGS  := 
AS       := C:/langs/tdm_x64/bin/as.exe


##
## User defined environment variables
##
CodeLiteDir:=C:\winoss\codelite
Objects0=$(IntermediateDirectory)/QPBO.cpp$(ObjectSuffix) $(IntermediateDirectory)/QPBO_extra.cpp$(ObjectSuffix) $(IntermediateDirectory)/QPBO_maxflow.cpp$(ObjectSuffix) $(IntermediateDirectory)/QPBO_postprocessing.cpp$(ObjectSuffix) $(IntermediateDirectory)/ciface.cpp$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild MakeIntermediateDirs
all: $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) $(@D)
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(SharedObjectLinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)
	@$(MakeDirCommand) "C:\workspace\Egonetworks\qpbo/.build-debug"
	@echo rebuilt > "C:\workspace\Egonetworks\qpbo/.build-debug/jnicpbo"

MakeIntermediateDirs:
	@$(MakeDirCommand) "./Debug"


$(IntermediateDirectory)/.d:
	@$(MakeDirCommand) "./Debug"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/QPBO.cpp$(ObjectSuffix): QPBO.cpp $(IntermediateDirectory)/QPBO.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "C:/workspace/Egonetworks/qpbo/jnicpbo/QPBO.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/QPBO.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/QPBO.cpp$(DependSuffix): QPBO.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/QPBO.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/QPBO.cpp$(DependSuffix) -MM "QPBO.cpp"

$(IntermediateDirectory)/QPBO.cpp$(PreprocessSuffix): QPBO.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/QPBO.cpp$(PreprocessSuffix) "QPBO.cpp"

$(IntermediateDirectory)/QPBO_extra.cpp$(ObjectSuffix): QPBO_extra.cpp $(IntermediateDirectory)/QPBO_extra.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "C:/workspace/Egonetworks/qpbo/jnicpbo/QPBO_extra.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/QPBO_extra.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/QPBO_extra.cpp$(DependSuffix): QPBO_extra.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/QPBO_extra.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/QPBO_extra.cpp$(DependSuffix) -MM "QPBO_extra.cpp"

$(IntermediateDirectory)/QPBO_extra.cpp$(PreprocessSuffix): QPBO_extra.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/QPBO_extra.cpp$(PreprocessSuffix) "QPBO_extra.cpp"

$(IntermediateDirectory)/QPBO_maxflow.cpp$(ObjectSuffix): QPBO_maxflow.cpp $(IntermediateDirectory)/QPBO_maxflow.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "C:/workspace/Egonetworks/qpbo/jnicpbo/QPBO_maxflow.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/QPBO_maxflow.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/QPBO_maxflow.cpp$(DependSuffix): QPBO_maxflow.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/QPBO_maxflow.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/QPBO_maxflow.cpp$(DependSuffix) -MM "QPBO_maxflow.cpp"

$(IntermediateDirectory)/QPBO_maxflow.cpp$(PreprocessSuffix): QPBO_maxflow.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/QPBO_maxflow.cpp$(PreprocessSuffix) "QPBO_maxflow.cpp"

$(IntermediateDirectory)/QPBO_postprocessing.cpp$(ObjectSuffix): QPBO_postprocessing.cpp $(IntermediateDirectory)/QPBO_postprocessing.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "C:/workspace/Egonetworks/qpbo/jnicpbo/QPBO_postprocessing.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/QPBO_postprocessing.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/QPBO_postprocessing.cpp$(DependSuffix): QPBO_postprocessing.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/QPBO_postprocessing.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/QPBO_postprocessing.cpp$(DependSuffix) -MM "QPBO_postprocessing.cpp"

$(IntermediateDirectory)/QPBO_postprocessing.cpp$(PreprocessSuffix): QPBO_postprocessing.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/QPBO_postprocessing.cpp$(PreprocessSuffix) "QPBO_postprocessing.cpp"

$(IntermediateDirectory)/ciface.cpp$(ObjectSuffix): ciface.cpp $(IntermediateDirectory)/ciface.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "C:/workspace/Egonetworks/qpbo/jnicpbo/ciface.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/ciface.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/ciface.cpp$(DependSuffix): ciface.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/ciface.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/ciface.cpp$(DependSuffix) -MM "ciface.cpp"

$(IntermediateDirectory)/ciface.cpp$(PreprocessSuffix): ciface.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/ciface.cpp$(PreprocessSuffix) "ciface.cpp"


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) -r ./Debug/


