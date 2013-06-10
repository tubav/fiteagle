FITeagle | Future Internet Testbed Experimentation and Management Framework 
===========================================================================

Bootstrap (install & run)
-------------------------
bash -c "$(curl -fsSkL fiteagle.org/bootstrap)"

Test
----
./src/main/bin/fiteaglectl test

Start
-----
./src/main/bin/fiteaglectl start

Further Information
-------------------
For further information please have a look at [http://fiteagle.org](http://fiteagle.org).

FAQ
---
* Q: FITeagle tests seem to hang while testing cryptography methods on Linux
* A: Setup rng-tools (it should e.g. use /dev/urandom)

* Q: I get annoying merge conflicts regarding core/src/main/resources/org/fiteagle/core/config/git.properties
* A: Just untrack it: git update-index --assume-unchanged core/src/main/resources/org/fiteagle/core/config/git.properties 
