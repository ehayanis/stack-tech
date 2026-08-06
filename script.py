#!/usr/bin/env python3

import csv
import sys


def convert_csv_to_properties(input_csv, output_properties):
    with open(input_csv, newline="", encoding="utf-8") as csvfile, \
         open(output_properties, "w", encoding="utf-8") as outfile:

        reader = csv.reader(csvfile)

        # Skip header
        next(reader, None)

        for row in reader:
            if len(row) < 2:
                continue

            package = row[0].strip()
            versions = row[1].strip()

            if not package or not versions:
                continue

            # Split versions separated by commas
            for version in versions.split(","):
                version = version.strip()

                if version:
                    outfile.write(f"{package}:{version}\n")


if __name__ == "__main__":
    if len(sys.argv) != 3:
        print(f"Usage: {sys.argv[0]} input.csv output.properties")
        sys.exit(1)

    convert_csv_to_properties(sys.argv[1], sys.argv[2])
