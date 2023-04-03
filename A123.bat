:: ---------------------------------------------------------------------
:: JAP COURSE - SCRIPT
:: SCRIPT CST8221 - Winter 2023
:: ---------------------------------------------------------------------
:: Begin of Script (A13 - W23)
:: ---------------------------------------------------------------------

CLS

:: LOCAL VARIABLES ....................................................

SET LIBDIR=lib
SET SRCDIR=src
SET BINDIR=bin
SET BINERR=picross-javac.err
SET JARNAME=picross.jar
SET JAROUT=picross-jar.out
SET JARERR=picross-jar.err
SET DOCDIR=doc
SET DOCPACK=src.picross
SET DOCERR=picross-javadoc.err
SET MAINCLASSSRC=src/picross/Game.java
SET MAINCLASSBIN=src.picross.Game

@echo off

ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
ECHO "@                                                                   @"
ECHO "@                   #       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@                  ##       @  A L G O N Q U I N  C O L L E G E  @  @"
ECHO "@                ##  #      @    JAVA APPLICATION PROGRAMMING    @  @"
ECHO "@             ###    ##     @        W I N T E R - 2 0 2 3       @  @"
ECHO "@          ###    ##        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@        ###    ##                                                  @"
ECHO "@        ##    ###                 ###                              @"
ECHO "@         ##    ###                ###                              @"
ECHO "@           ##    ##               ###   #####  ##     ##  #####    @"
ECHO "@         (     (      ((((()      ###       ## ###   ###      ##   @"
ECHO "@     ((((     ((((((((     ()     ###   ######  ###  ##   ######   @"
ECHO "@        ((                ()      ###  ##   ##   ## ##   ##   ##   @"
ECHO "@         ((((((((((( ((()         ###   ######    ###     ######   @"
ECHO "@         ((         ((           ###                               @"
ECHO "@          (((((((((((                                              @"
ECHO "@   (((                      ((                                     @"
ECHO "@    ((((((((((((((((((((() ))                                      @"
ECHO "@         ((((((((((((((((()                                        @"
ECHO "@                                                                   @"
ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"

ECHO "[LABS SCRIPT ---------------------]"

ECHO "1. Compiling ......................"
javac -Xlint -cp ".;%SRCDIR%;" %MAINCLASSSRC% -d %BINDIR% 2> %BINERR%


ECHO "2. Creating Jar ..................."
::XCOPY %SRCDIR% %BINDIR% /E /Y

CD bin
jar cvfe %JARNAME% %MAINCLASSBIN% . > ../%JAROUT% 2> ../%JARERR%

ECHO "3. Creating Javadoc ..............."
CD ..
javadoc -cp ".;%BINDIR%;*" -d %DOCDIR% -subpackages %DOCPACK% 2> %DOCERR%

CD bin
ECHO "4. Running Jar ...................."
START java -jar %JARNAME%
::[Another option]: start java --module-path "/SOFT/COPY/dev/LIBS/javafx/lib;/SOFT/COPY/db/derby/lib" --add-modules javafx.controls,javafx.fxml -jar CST8221.jar

::ECHO "Running  (ouside jar) ............"
:: start java -cp ".;bin;/SOFT/copy/dev/java/javafx/lib/*" CST8221.Main

CD ..


ECHO "[END OF SCRIPT -------------------]"
ECHO "                                   "

PAUSE
:: ---------------------------------------------------------------------
:: End of Script (A13 - W23)
:: ---------------------------------------------------------------------
