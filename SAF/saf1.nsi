!include "MUI2.nsh" 
Name "SAF" 
OutFile "Instalador SAF.exe" 
InstallDir $PROGRAMFILES\SAF 

InstallDirRegKey HKLM "Software\SAF" "Install_Dir" 

RequestExecutionLevel admin

!define MUI_ABORTWARNING
!define MUI_LANGDLL_REGISTRY_ROOT "HKCU"
;define MUI_LANGDLL_REGISTRY_KEY "Software\SAF"
!define MUI_LANGDLL_REGISTRY_VALUENAME "Installer Language"

!insertmacro MUI_PAGE_LICENSE "License.txt"
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES

!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

!insertmacro MUI_LANGUAGE "English"
!insertmacro MUI_LANGUAGE "French"
!insertmacro MUI_LANGUAGE "German"
!insertmacro MUI_LANGUAGE "Spanish"
!insertmacro MUI_LANGUAGE "SpanishInternational"
!insertmacro MUI_LANGUAGE "SimpChinese"
!insertmacro MUI_LANGUAGE "TradChinese"
!insertmacro MUI_LANGUAGE "Japanese"
!insertmacro MUI_LANGUAGE "Korean"
!insertmacro MUI_LANGUAGE "Italian"
!insertmacro MUI_LANGUAGE "Dutch"
!insertmacro MUI_LANGUAGE "Danish"
!insertmacro MUI_LANGUAGE "Swedish"
!insertmacro MUI_LANGUAGE "Norwegian"
!insertmacro MUI_LANGUAGE "NorwegianNynorsk"
!insertmacro MUI_LANGUAGE "Finnish"
!insertmacro MUI_LANGUAGE "Greek"
!insertmacro MUI_LANGUAGE "Russian"
!insertmacro MUI_LANGUAGE "Portuguese"
!insertmacro MUI_LANGUAGE "PortugueseBR"
!insertmacro MUI_LANGUAGE "Polish"
!insertmacro MUI_LANGUAGE "Ukrainian"
!insertmacro MUI_LANGUAGE "Czech"
!insertmacro MUI_LANGUAGE "Slovak"
!insertmacro MUI_LANGUAGE "Croatian"
!insertmacro MUI_LANGUAGE "Bulgarian"
!insertmacro MUI_LANGUAGE "Hungarian"
!insertmacro MUI_LANGUAGE "Thai"
!insertmacro MUI_LANGUAGE "Romanian"
!insertmacro MUI_LANGUAGE "Afrikaans"
!insertmacro MUI_LANGUAGE "Catalan"
!insertmacro MUI_LANGUAGE "Esperanto" 

!insertmacro MUI_RESERVEFILE_LANGDLL

Section "SAF (requerido)" 
SectionIn RO 
SetOutPath $INSTDIR 

;File "SAF.exe" 
;File "SAF.VBS" 
File "SAF.bat" 
File "SAF.jar" 
File "jre-7u45-windows-i586.exe" 
execwait $INSTDIR\jre-7u45-windows-i586.exe
WriteRegStr HKLM SOFTWARE\SAF "Install_Dir" "$INSTDIR" 

SetOutPath $INSTDIR\report
SetOutPath $INSTDIR\data 
File "data\*.*" 
SetOutPath $INSTDIR\data\vsiaf.tmp

SetOutPath $INSTDIR\lib 
File "lib\*.*" 

WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SAF" "DisplayName" "SAF" 
WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SAF" "UninstallString" '"$INSTDIR\uninstall.exe"' 
WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SAF" "NoModify" 1 
WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SAF" "NoRepair" 1 
WriteUninstaller "uninstall.exe" 

SectionEnd 

;Section "Start Menu Shortcuts" 

;CreateDirectory "$SMPROGRAMS\SAF" 
;CreateShortCut "$SMPROGRAMS\SAF\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0 
;CreateShortCut "$SMPROGRAMS\SAF\SAF.lnk" "$INSTDIR\SAF.bat" "" "$INSTDIR\SAF.bat" 0 

;SectionEnd 

Section "Uninstall" 

DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\SAF" 
DeleteRegKey HKLM "SOFTWARE\SAF" 

Delete $INSTDIR\SAF.jar 
Delete $INSTDIR\SAF.bat 
;Delete $INSTDIR\SAF.exe 
Delete $INSTDIR\*.*

Delete $INSTDIR\data\*.*
Delete $INSTDIR\SAF\data\vsiaf.tmp
RMDIR $INSTDIR\SAF\data
RMDIR $INSTDIR\SAF\report

Delete $INSTDIR\lib\*.*" 
RMDIR $INSTDIR\SAF\lib" 

RMDIR $INSTDIR\SAF" 

SectionEnd

Function un.onInit
!insertmacro MUI_UNGETLANGUAGE

FunctionEnd