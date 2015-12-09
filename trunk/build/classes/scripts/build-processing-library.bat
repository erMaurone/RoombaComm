::
:: Builds the distribution zip file that contains a properly formatted 
:: Processing library.  Unzip the resulting file in the 'libraries' directory
:: in Processing to install.
::
:: Before running this script, run 'build-jar.sh'
::

cd packaging
rd /s /q processing
md processing\com.hackingroomba.roombacomm\library
copy ..\com.hackingroomba.roombacomm.jar      processing\com.hackingroomba.roombacomm\library\com.hackingroomba.roombacomm.jar
copy processing-export.txt  processing\com.hackingroomba.roombacomm\library\export.txt
copy ..\rxtxlib\*           processing\com.hackingroomba.roombacomm\library
md processing\com.hackingroomba.roombacomm\jargs.examples
xcopy ..\processing-jargs.examples processing\com.hackingroomba.roombacomm\jargs.examples /s
copy ..\README              processing\com.hackingroomba.roombacomm\README
copy processing-readme.txt  processing\com.hackingroomba.roombacomm\README-processing.txt
cd processing
..\..\7z a com.hackingroomba.roombacomm-processing.zip com.hackingroomba.roombacomm -r
copy com.hackingroomba.roombacomm-processing.zip ..\..
cd ..\..
