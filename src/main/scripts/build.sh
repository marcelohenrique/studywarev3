#!/bin/bash
# This is a simple build script, place your post-deploy but pre-start commands
# in this script.  This script gets executed directly, so it could be python,
# php, ruby, etc.

set -e

if [ -z $OPENSHIFT_POSTGRESQL_DB_HOST ]
then
    echo 1>&2
    echo "Could not find database." 1>&2
#    echo "rhc-ctl-app -a $OPENSHIFT_APP_NAME -e add-mysql-5.1" 1>&2
#    echo "then make a sample commit (add whitespace somewhere) and re-push" 1>&2
    echo 1>&2
    exit 5
fi

# Confirm database exists, if not create it
if /usr/bin/psql -U "$OPENSHIFT_POSTGRESQL_DB_USERNAME" -h "$OPENSHIFT_POSTGRESQL_DB_HOST" -d "$PGDATABASE" -c "select 1;;" > /dev/null
then
#    echo
#    echo "Schema not found!  Importing schema from .openshift/action_hooks/redmine.sql"
#    echo
    /usr/bin/psql -U "$OPENSHIFT_POSTGRESQL_DB_USERNAME" -h "$OPENSHIFT_POSTGRESQL_DB_HOST" -d "$PGDATABASE" -f "$1"
    echo
    echo "done."
# else
#     echo "Database found, skipping import."
fi
