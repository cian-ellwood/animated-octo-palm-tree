# With this script I can dynamically update the version of my jar and image
# I am using Pythons PEP8 standards for this script https://peps.python.org/pep-0008/

import datetime
import json
import yaml


def format_current_week_and_year():
    current_date = datetime.datetime.now()
    current_year = current_date.strftime("%Y")
    current_week = current_date.strftime("%W")

    calendar_version = current_year + '.' + current_week
    print(calendar_version)
    return calendar_version


def set_new_service_version(calendar_version):
    with open("py-scripts/scriptHelpers/version.json") as version_data:
        data = json.load(version_data)
        current_version = data["version"]
        # If I take this in as an Integer I can increment easier down the line
        # There would be conflicts for this versioning by incrementing manually
        # Usually this wouldn't be ran until the CI stage and we would use the CI build number as minor version
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
        return updated_version_json_file, new_version


def update_version_file(updated_version_json_file):
    # if we used json.dump here it would cause the json data to double encode and break the script
    with open("py-scripts/scriptHelpers/version.json", "w") as old_version_data:
        old_version_data.write(updated_version_json_file)


def update_k8s_version(new_version):
    with open("k8s/apiLogger/Chart.yaml") as k8s_version:
        updated_k8_data = yaml.load(k8s_version, Loader=yaml.FullLoader)
        # Rather annoyingly regardless of combination of string when
        # we format it the double quotes are dropped when we update the yaml

        # Set chart version to be in line with application version
        updated_k8_data["appVersion"] = new_version
        updated_k8_data["version"] = new_version

        return updated_k8_data


def write_new_k8s_version(updated_k8_data):
    with open("k8s/apiLogger/Chart.yaml", "w") as old_k8s_version:
        yaml.dump(updated_k8_data, stream=old_k8s_version, default_flow_style=False, sort_keys=False)


def main():
    calendar_version = format_current_week_and_year()
    updated_version_json_file, new_version = set_new_service_version(calendar_version)
    update_version_file(updated_version_json_file)
    updated_k8s_data = update_k8s_version(new_version)
    write_new_k8s_version(updated_k8s_data)


if __name__ == "__main__":
    main()
