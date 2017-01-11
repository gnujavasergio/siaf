Set WshShell = CreateObject("WScript.Shell")
currentDirectory = left(WScript.ScriptFullName,(Len(WScript.ScriptFullName))-(len(WScript.ScriptName))) 
WshShell.Run chr(34) & currentDirectory & "SAF.bat" & Chr(34), 0 
Set WshShell = Nothing 