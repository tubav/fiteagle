FITeagle | Future Internet Testbed Experimentation and Management Framework 
---------------------------------------------------------------------------

Bootstrap: bash -c "$(curl -fsSkL fiteagle.org/bootstrap)"

For further information please have a look at [http://fiteagle.org](http://fiteagle.org).

To get rid of annoying merge conflicts regarding the core/src/main/resources/org/fiteagle/core/config/git.properties file, just "untrack" it by:

git update-index --assume-unchanged core/src/main/resources/org/fiteagle/core/config/git.properties 