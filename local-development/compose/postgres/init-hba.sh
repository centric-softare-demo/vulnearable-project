#!/bin/sh
set -e
cp /tmp/pg_hba.conf "$PGDATA/pg_hba.conf"
chmod 600 "$PGDATA/pg_hba.conf"
