
@echo off
cd /d %cd%
echo -----------------------------------------------
echo %cd%
echo -----------------------------------------------
git status
git add .
git commit -m "others %date% %time%"
git push
pause