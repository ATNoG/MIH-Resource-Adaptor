MIH-Resource-Adaptor
====================

MIH Resource Adaptor for Mobicents JAIN SLEE

Description
-----------

MIH Resource Adaptor (RA) is the JAIN SLEE 1.1 RA for the MIH Protocol, which
can be used in Mobicents JAIN SLEE platform.

At the moment, it is only responsible for network I/O operations and
interaction with JAIN SLEE Service Building Blocks (SBBs), allowing SBB to
receive and to send MIH messages. The management of connection between peers
is still made by ODTONE-MIHF, which is the parent project of this RA. In the
future, we prented to support the management of connection between peers
directly in the MIH RA.

You can check more info about ODTONE in our
[website](http://atnog.av.it.pt/odtone/)
or [GitHub repository](https://github.com/ATNoG/ODTONE).

More info about Mobicents JAIN SLEE platform can be found
[here](http://www.mobicents.org/slee/intro.html).


Installation instructions
-------------------------

1.  Define JBOSS_HOME

		export JBOSS_HOME="<path to JBoss>"

2.  Compile and install

		mvn clean install


Where to get help
-----------------

You can ask your question in the ODTONE
[mailing list](https://atnog.av.it.pt/cgi-bin/mailman/listinfo) or create an
issue in [GitHub repository](https://github.com/ATNOG/MIH-Resource-Adaptor).

Contribution guidelines
-----------------------

First make sure that your patch follows these rules:

1. It works!! :)
2. No trailing white spaces.
3. Keep the code style consistent.
4. Use tabs and no spaces, except for alignment where you should use spaces
   instead. Tab width is 4.

After that you can create a pull request in
[GitHub repository](https://github.com/ATNOG/MIH-Resource-Adaptor).

Contributor list
----------------

	Carlos Guimarães <cguimaraes@av.it.pt>

External Contributors:

	You can be the first one :)

Credits
-------

The code developed for the MIH Resource Adaptor was based on the examples
provided by Mobicents software.