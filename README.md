To build and run the server:

```bash
bazel run //server
```

To build and run the client:

```bash
bazel run //client
```

To generate a graph of dependencies:

```bash
bazel cquery "deps(//server + //client) intersect //..." --output=graph | dot -Tjpeg > graph.jpeg
```
