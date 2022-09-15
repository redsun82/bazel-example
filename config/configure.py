import sys
import configparser
import argparse


def options():
    parser = argparse.ArgumentParser()
    parser.add_argument("mode", choices=["cpp", "java"])
    parser.add_argument("input")
    parser.add_argument("output")
    return parser.parse_args()


_templates = {
    "cpp": """
namespace hello {
constexpr int PORT = %(port)s;
}
""",
    "java": """
package hello;
public class Config {
    public static final int PORT = %(port)s;
}
""",
}

def main(opts):
    config = configparser.ConfigParser()
    config.read(opts.input)
    with open(opts.output, "w") as out:
        print(_templates[opts.mode] % config["config"], file=out)


if __name__ == "__main__":
    main(options())
