# Local development environment

* Create local postgres container:

```shell
docker run -d --name polar-postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:14.4
```

* Stop local postgres container:

```shell
docker stop polar-postgres
```

* Start local postgres container

```shell
docker start polar-postgres
```

* Remove local postgres container:

```shell
docker rm -fv polar-postgres
```
