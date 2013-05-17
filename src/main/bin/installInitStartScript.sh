 
#!/usr/bin/env bash

echo "creating the start script in init.d.."


cat <<EOF >./fiteagleStartScript
#! /bin/sh
### BEGIN INIT INFO
# Provides:          fiteagle
# Required-Start:    
# Required-Stop:     
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Start script for /etc/init.d
# Description:       
### END INIT INFO
# Author: Name <fiteagle@fokus.fraunhofer.de>

# Aktionen
case "\$1" in
    start)
	cd "${PWD}"
	sudo -u fiteagle -H sh -c "screen -S fiteagle -d -m -L ./src/main/bin/fiteagle.sh start"
        ;;
    restart)
        cd "${PWD}"
	sudo -u fiteagle -H sh -c "screen -S fiteagle -d -m -L ./src/main/bin/fiteagle.sh start"
        ;;
esac

exit 0 
EOF

cp fiteagleStartScript /etc/init.d/
rm fiteagleStartScript

echo "installing the start script in init.d.."

sudo chmod 755 /etc/init.d/fiteagleStartScript
# 
update-rc.d fiteagleStartScript defaults