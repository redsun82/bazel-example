package(default_visibility = ["//visibility:public"])

load("@rules_proto//proto:defs.bzl", "proto_library")
load("@rules_proto_grpc//cpp:defs.bzl", "cpp_grpc_library")
load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")

proto_library(
    name = "hello",
    srcs = ["hello.proto"],
)

cpp_grpc_library(
    name = "cc",
    protos = [":hello"],
)

java_grpc_library(
    name = "java",
    protos = [":hello"],
)
