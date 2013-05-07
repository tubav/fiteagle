FITeagle | Future Internet Testbed Experimentation and Management Framework 
===========================================================================

Bootstrap (install & run)
-------------------------
bash -c "$(curl -fsSkL fiteagle.org/bootstrap)"

Test
----
./src/main/bin/fiteagle.sh test

Start
-----
./src/main/bin/fiteagle.sh start

Further Information
-------------------
For further information please have a look at [http://fiteagle.org](http://fiteagle.org).

FAQ
---
* Q: FITeagle tests seem to hang while testing cryptography methods on CentOS
* A: Use Oracle Java or try the fix described at http://development.adaptris.com/users/lchan/blog/2012/06/15/slow-java-crypto-performance-on-linux/

* Q: I get annoying merge conflicts regarding core/src/main/resources/org/fiteagle/core/config/git.properties
* A: Just untrack it: git update-index --assume-unchanged core/src/main/resources/org/fiteagle/core/config/git.properties 
