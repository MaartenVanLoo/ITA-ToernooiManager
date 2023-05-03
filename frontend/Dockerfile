FROM httpd:2.4
# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY dist /usr/local/apache2/htdocs/
#Copy custom config
COPY apache-httpd.conf /usr/local/apache2/conf/httpd.conf
