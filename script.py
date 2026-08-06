#!/usr/bin/env python3

import csv
import sys
from pathlib import Path


def clean_value(value: str) -> str:
    """
    Remove spaces, duplicated quotes and unmatched surrounding quotes.
    """
    value = value.strip()

    # Convert duplicated CSV quotes: "" -> "
    while '""' in value:
        value = value.replace('""', '"')

    # Remove all quotes accidentally placed around the value
    value = value.strip().strip('"').strip()

    return value


def fix_csv(input_csv: Path, cleaned_csv: Path) -> None:
    """
    Fix malformed quoting in the original CSV.

    The line is split only at the first comma because all following
    commas are considered part of the versions column.
    """
    with input_csv.open("r", encoding="utf-8-sig", errors="replace") as source, \
            cleaned_csv.open("w", encoding="utf-8", newline="") as destination:

        writer = csv.writer(
            destination,
            quoting=csv.QUOTE_MINIMAL,
            lineterminator="\n",
        )

        writer.writerow(["Package", "Malicious Versions"])

        for line_number, raw_line in enumerate(source, start=1):
            line = raw_line.strip()

            if not line:
                continue

            # Skip the original header
            if line_number == 1 and line.lower().startswith("package"):
                continue

            if "," not in line:
                print(
                    f"Warning: line {line_number} ignored because "
                    f"it does not contain a comma: {line!r}",
                    file=sys.stderr,
                )
                continue

            # Split ONLY at the first comma
            package_raw, versions_raw = line.split(",", maxsplit=1)

            package = clean_value(package_raw)
            versions = clean_value(versions_raw)

            if not package or not versions:
                print(
                    f"Warning: incomplete line {line_number} ignored: {line!r}",
                    file=sys.stderr,
                )
                continue

            # csv.writer will automatically quote the versions column
            # when it contains several comma-separated versions.
            writer.writerow([package, versions])


def csv_to_properties(cleaned_csv: Path, properties_file: Path) -> None:
    """
    Convert the corrected CSV to one package:version entry per line.
    """
    with cleaned_csv.open("r", encoding="utf-8", newline="") as source, \
            properties_file.open("w", encoding="utf-8", newline="\n") as destination:

        reader = csv.DictReader(source)

        for line_number, row in enumerate(reader, start=2):
            package = clean_value(row.get("Package", ""))
            versions_value = clean_value(row.get("Malicious Versions", ""))

            if not package or not versions_value:
                print(
                    f"Warning: incomplete cleaned CSV line {line_number} ignored",
                    file=sys.stderr,
                )
                continue

            versions = [
                clean_value(version)
                for version in versions_value.split(",")
                if clean_value(version)
            ]

            for version in versions:
                destination.write(f"{package}:{version}\n")


def main() -> int:
    if len(sys.argv) not in (2, 3):
        print(
            f"Usage: {sys.argv[0]} INPUT.csv [OUTPUT.properties]",
            file=sys.stderr,
        )
        return 1

    input_csv = Path(sys.argv[1])

    if not input_csv.is_file():
        print(f"Error: file not found: {input_csv}", file=sys.stderr)
        return 1

    if len(sys.argv) == 3:
        properties_file = Path(sys.argv[2])
    else:
        properties_file = input_csv.with_suffix(".properties")

    cleaned_csv = input_csv.with_name(
        f"{input_csv.stem}_cleaned{input_csv.suffix}"
    )

    fix_csv(input_csv, cleaned_csv)
    csv_to_properties(cleaned_csv, properties_file)

    print(f"Corrected CSV: {cleaned_csv}")
    print(f"Properties file: {properties_file}")

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
