# InfluxDB

[Docker image documentation](https://docs.docker.com/samples/library/influxdb/#configuration)

To generate a [configuration file](https://docs.influxdata.com/influxdb/v1.6/administration/config/#configuration-overview):

```
docker run --rm influxdb influxd config > influxdb.conf
```

Current database list

```
curl -G http://localhost:8086/query?pretty=true --data-urlencode "db=glances" --data-urlencode "q=SHOW DATABASES"
```

Create a new database ?

```
curl -XPOST 'http://localhost:8086/query' --data-urlencode 'q=CREATE DATABASE mydb'
```

```
curl --user admin:kraken http://localhost:8086/query --data-urlencode "q=SHOW USERS"
curl --user admin:kraken http://localhost:8086/query --data-urlencode "q=CREATE USER kojiro.sazaki WITH PASSWORD 'pwd'"

```

Open client

```
docker exec -it kraken-influxdb-dev /bin/bash
influx --username admin --password kraken
show databases
use telegraf
show measurements
select * from cpu limit 10
```

## Gatling Graphite input

[Documentation - requires graphite](https://gatling.io/docs/3.0/realtime_monitoring/)
[BM Blog post - direct to influxDB](https://www.blazemeter.com/blog/gatling-tests-monitoring-with-grafana-and-influxdb)
[Graphite templates](https://github.com/influxdata/influxdb/blob/master/services/graphite/README.md)

# Telegraf

[Docker image documentation](https://docs.docker.com/samples/library/telegraf/)

To generate a configuration file:

```
docker run --rm telegraf telegraf config > telegraf.conf
```

# Grafana

[Docker image documentation](http://docs.grafana.org/installation/docker/)

Available at [http://127.0.0.1:3000](http://127.0.0.1:3000) admin/kraken

To generate a configuration file:

```
docker run -d -p 3000:3000 --rm --name=grafana grafana/grafana:6.7.1
docker cp grafana:/etc/grafana/grafana.ini grafana.ini
docker stop grafana
```

## Provisioning
[Blog post](https://ops.tips/blog/initialize-grafana-with-preconfigured-dashboards/#configuring-grafana)
[Documentation](http://docs.grafana.org/administration/provisioning/)
