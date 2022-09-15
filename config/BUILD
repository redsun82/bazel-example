py_binary(
    name = "configure",
    srcs = ["configure.py"],
)

genrule(
    name = "gen-cc",
    srcs = ["config.ini"],
    outs = ["config.h"],
    cmd = "$(location :configure) cpp $< $@",
    tools = [":configure"],
)

cc_library(
    name = "cc",
    hdrs = [":gen-cc"],
    visibility = ["//visibility:public"],
)

genrule(
    name = "gen-java",
    srcs = ["config.ini"],
    outs = ["Config.java"],
    cmd = "$(location :configure) java $< $@",
    tools = [":configure"],
)

java_library(
    name = "java",
    srcs = [":gen-java"],
    visibility = ["//visibility:public"],
)
