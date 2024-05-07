# With this script I can dynamically update the version of my jar and image
# I am using Pythons PEP8 standards for this script https://peps.python.org/pep-0008/

import datetime
import json


def format_current_week_and_year():
    current_date = datetime.datetime.now()
    current_year = current_date.strftime("%Y")
    current_week = current_date.strftime("%W")

    calendar_version = current_year + '.' + current_week
    print(calendar_version)
    return calendar_version


def set_new_service_version(calendar_version):
    with open("src/main/resources/scripts/scriptHelpers/version.json") as version_data:
        data = json.load(version_data)
        current_version = data["version"]
        # If I take this in as an Integer I can increment easier down the line
        minor_version = int(data["current_minor_version"])

        # if the current year/week is not in the current version, use new year/week and reset minor version
        if calendar_version not in current_version:
            # reset minor counter to 0
            minor_version = 0
            new_version = calendar_version + '.' + str(minor_version)
        else:
            # increment minor version
            minor_version += 1
            new_version = calendar_version + '.' + str(minor_version)

        data["version"] = new_version
        data["current_minor_version"] = str(minor_version)

        # We prettify the output json
        updated_version_json_file = json.dumps(data, indent=4)
        return updated_version_json_file


def update_version_file(updated_version_json_file):
    # using json.dump here causes the json data to double encode and break the script
    with open("src/main/resources/scripts/scriptHelpers/version.json", "w") as old_version_data:
        old_version_data.write(updated_version_json_file)


def main():
    calendar_version = format_current_week_and_year()
    updated_version_json_file = set_new_service_version(calendar_version)
    update_version_file(updated_version_json_file)


if __name__ == "__main__":
    main()
