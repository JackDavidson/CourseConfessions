every time you are ready to make a change:

rm -rf CourseConfessions
mkdir CourseConfessions
cd ./CourseConfessions
git init
git remote add origin https://github.com/JackDavidson/CourseConfessions.git
git pull -u origin master


IMPORTANT:
git checkout -b NAME

make your changes, and git add whatever

git commit -m "GOOD, USEFUL COMMENTS"
git push -u origin NAME

