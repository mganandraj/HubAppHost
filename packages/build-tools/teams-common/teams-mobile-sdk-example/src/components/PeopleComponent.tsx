/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import * as React from "react";
import {
  Button,
  Image,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  View,
} from "react-native";
import {
  ContactCardItem,
  ContactCardItemValue,
  ContactCardItemValueType,
  ContactCardMultiValueItem,
  ContactCardSingleValueItem,
  IdType,
  PeopleProvider,
  Person,
  PersonContactMetadata,
  PersonPresenceStatus,
  PersonProfilePicture,
  Resource,
  TraceLogger,
  User,
  UserId,
} from "teams-mobile-sdk";

import { Utilities } from "./utilities/Utilities";

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    padding: 20,
  },
  margin: {
    margin: 10,
  },
  value: {
    fontWeight: "bold",
  },
  label: {},
  input: {
    borderColor: '#d6d7da',
    borderWidth: 0.5,
    margin: 4,
    marginBottom: 8,
    flex: 1,
  },
});

export interface PeopleComponentProps {
  currentUser: User;
}

interface PeopleComponentState {
  UserId?: UserId;
  aliasSubstitution: string;
  person?: Person;
  personProfilePicture?: PersonProfilePicture;
  personPresenceStatus?: PersonPresenceStatus;
  personContactMetadata?: PersonContactMetadata;
}
export class PeopleComponent extends React.Component<
  PeopleComponentProps,
  PeopleComponentState
> {
  private static readonly LOG_TAG: string = "PeopleComponent";

  constructor(props: PeopleComponentProps) {
    super(props);
    this.state = {
      aliasSubstitution: ""
    };
  }

  public render() {
    const personDetails =
      this.state && this.state.person ? (
        <>
          <Text>
            <Text style={styles.label}>firstName:</Text>
            <Text style={styles.value}> {this.state.person.firstName}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>lastName:</Text>
            <Text style={styles.value}> {this.state.person.lastName}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>displayName:</Text>
            <Text style={styles.value}> {this.state.person.displayName}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>mri:</Text>
            <Text style={styles.value}> {this.state.person.mri}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>upn:</Text>
            <Text style={styles.value}> {this.state.person.upn}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>aadId:</Text>
            <Text style={styles.value}> {this.state.person.aadId}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>tenantId:</Text>
            <Text style={styles.value}> {this.state.person.tenantId}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>officeLocation:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.person.officeLocation}
            </Text>
          </Text>
          <Text>
            <Text style={styles.label}>jobTitle:</Text>
            <Text style={styles.value}> {this.state.person.jobTitle}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>company:</Text>
            <Text style={styles.value}> {this.state.person.company}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>department:</Text>
            <Text style={styles.value}> {this.state.person.department}</Text>
          </Text>
          {this.state.person.postalAddresses.map((address, index) => (
            <Text key={index}>
              <Text style={styles.label}>postalAddress:</Text>
              <Text style={styles.value}>
                {" "}
                {address.type}: {address.street}, {address.city}{" "}
                {address.postalCode}
              </Text>
            </Text>
          ))}
          {this.state.person.phoneNumbers.map((phone, index) => (
            <Text key={index}>
              <Text style={styles.label}>phoneNumber:</Text>
              <Text style={styles.value}>
                {" "}
                {phone.type}: {phone.number}
              </Text>
            </Text>
          ))}
          <Text>
            <Text style={styles.label}>email:</Text>
            <Text style={styles.value}> {this.state.person.email}</Text>
          </Text>
          {this.state.person.emails.map((email, index) => (
            <Text key={index}>
              <Text style={styles.label}>email:</Text>
              <Text style={styles.value}>
                {" "}
                {index}: {email}
              </Text>
            </Text>
          ))}
          <Text>
            <Text style={styles.label}>contactNotes:</Text>
            <Text style={styles.value}> {this.state.person.contactNotes}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>userType:</Text>
            <Text style={styles.value}> {this.state.person.userType}</Text>
          </Text>
          <Text>
            <Text style={styles.label}>isBlocked:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.person.isBlocked ? "true" : "false"}
            </Text>
          </Text>
          <Text>
            <Text style={styles.label}>isSpeedDialContact:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.person.isSpeedDialContact ? "true" : "false"}
            </Text>
          </Text>
        </>
      ) : null;

    const personProfilePicture =
      this.state && this.state.personProfilePicture ? (
        <>
          <Image
            source={{
              uri: this.state.personProfilePicture.profilePictureUrl,              
            }}
            style={{ width: 100, height: 100 }}
          />
          <Text>
            <Text style={styles.label}>profilePictureUrl:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.personProfilePicture.profilePictureUrl}
            </Text>
          </Text>          
        </>
      ) : null;

    const personPresenceStatus =
      this.state && this.state.personPresenceStatus ? (
        <>
          <Text>
            <Text style={styles.label}>presenceStatus:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.personPresenceStatus.presenceStatus}
            </Text>
          </Text>
          <Text>
            <Text style={styles.label}>customStatusMessage:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.personPresenceStatus.customStatusMessage}
            </Text>
          </Text>
          <Text>
            <Text style={styles.label}>customStatusMessageTime:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.personPresenceStatus.customStatusMessageTime}
            </Text>
          </Text>
          <Text>
            <Text style={styles.label}>oofStatusMessage:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.personPresenceStatus.oofStatusMessage}
            </Text>
          </Text>
          <Text>
            <Text style={styles.label}>oofStatusMessageTime:</Text>
            <Text style={styles.value}>
              {" "}
              {this.state.personPresenceStatus.oofStatusMessageTime}
            </Text>
          </Text>
        </>
      ) : null;

    const personContactMetadata =
      this.state && this.state.personContactMetadata ? (
        <>
          {this.state.personContactMetadata.contactMetadata
            ? Object.keys(
                this.state.personContactMetadata.contactMetadata
              ).map((key) => this.renderContactMetadata(key))
            : null}
        </>
      ) : null;

    return (
      <ScrollView contentContainerStyle={styles.container}>
        <Text>
          <Text style={styles.label}>Currently user:</Text>
          <Text style={styles.value}>
            {" "}
            {this.props.currentUser.principalName}
          </Text>
        </Text>
        <View style={{flex: 1, flexDirection: "row"}}>
          <Text style={styles.label}>Substitute alias:</Text>
          <TextInput
            style = {styles.input}
            onChangeText={(text) => this.setState({ ...this.state, aliasSubstitution: text })}
            value={this.state.aliasSubstitution}>
          </TextInput>
        </View>
        <Text>
          <Text style={styles.label}>UPN to query:</Text>
          <Text style={styles.value}>
            {" "}
            {this.UserIdFrom(this.props.currentUser, "aadUpn").userId}
          </Text>
        </Text>
        <Button
          onPress={() => this.handleGetPeopleDetailsBtnPress("mri")}
          title={Resource.getLocalizedString(
            "get_people_details_by_mri_btn_title"
          )}
          accessibilityLabel={Resource.getLocalizedString(
            "get_people_details_by_mri_btn_accessibility_label"
          )}
        />
        <Button
          onPress={() => this.handleGetPeopleDetailsBtnPress("aadUpn")}
          title={Resource.getLocalizedString(
            "get_people_details_by_upn_btn_title"
          )}
          accessibilityLabel={Resource.getLocalizedString(
            "get_people_details_by_upn_btn_accessibility_label"
          )}
        />
        <Button
          onPress={() => this.handleGetPeopleDetailsBtnPress("aadObjectId")}
          title={Resource.getLocalizedString(
            "get_people_details_by_aad_btn_title"
          )}
          accessibilityLabel={Resource.getLocalizedString(
            "get_people_details_by_aad_btn_accessibility_label"
          )}
        />
        {personDetails}
        <Button
          onPress={() => this.handleGetProfilePhotoBtnPress("aadUpn")}
          title={Resource.getLocalizedString("get_profile_picture_btn_title")}
          accessibilityLabel={Resource.getLocalizedString(
            "get_profile_picture_btn_accessibility_label"
          )}
        />
        {personProfilePicture}
        <Button
          onPress={() => this.handleGetPresenceBtnPress("aadUpn")}
          title={Resource.getLocalizedString("get_presence_btn_title")}
          accessibilityLabel={Resource.getLocalizedString(
            "get_presence_btn_accessibility_label"
          )}
        />
        {personPresenceStatus}
        <Button
          onPress={() => this.handleGetContactMetadataBtnPress("aadUpn")}
          title={Resource.getLocalizedString("get_contact_metadata_btn_title")}
          accessibilityLabel={Resource.getLocalizedString(
            "get_contact_metadata_btn_accessibility_label"
          )}
        />
        {personContactMetadata}
        <Button
          onPress={() => this.handleViewProfilePictureForPerson("mri")}
          title={Resource.getLocalizedString(
            "view_profile_picture_by_mri_btn_title"
          )}
          accessibilityLabel={Resource.getLocalizedString(
            "view_profile_picture_by_mri_btn_accessibility_label"
          )}
        />
        <Button
          onPress={() => this.handleViewProfilePictureForPerson("aadUpn")}
          title={Resource.getLocalizedString(
            "view_profile_picture_by_upn_btn_title"
          )}
          accessibilityLabel={Resource.getLocalizedString(
            "view_profile_picture_by_upn_btn_accessibility_label"
          )}
        />
        <Button
          onPress={() => this.handleViewProfilePictureForPerson("aadObjectId")}
          title={Resource.getLocalizedString(
            "view_profile_picture_by_aad_btn_title"
          )}
          accessibilityLabel={Resource.getLocalizedString(
            "view_profile_picture_by_aad_btn_accessibility_label"
          )}
        />
      </ScrollView>
    );
  }

  private renderContactMetadata(contactMetadataType) {
    if (
      !this.state.personContactMetadata ||
      !this.state.personContactMetadata.contactMetadata
    ) {
      return null;
    }
    const cardEntries: ContactCardItem[] = this.state.personContactMetadata
      .contactMetadata[contactMetadataType];

    const cardEntriesElements: JSX.Element[] = [];

    for (const entry of cardEntries) {
      if ("value" in entry) {
        const singleValue = entry as ContactCardSingleValueItem;
        const view = (
          <View key={`${contactMetadataType}.${singleValue.header}`}>
            <Text>
              {" "}
              <Text style={styles.label}>ContactCardSingleValueItem</Text>
              <Text style={styles.value}> '{singleValue.header}'</Text>
            </Text>
            {this.renderContactMetadataValue(
              contactMetadataType,
              singleValue.value
            )}
          </View>
        );
        cardEntriesElements.push(view);
      } else if ("values" in entry) {
        const multiValue = entry as ContactCardMultiValueItem;
        const view = (
          <View key={`${contactMetadataType}.${multiValue.header}`}>
            <Text>
              {" "}
              <Text style={styles.label}>ContactCardMultiValueItem</Text>
              <Text style={styles.value}> '{multiValue.header}'</Text>
            </Text>
            {multiValue.values.map((value) =>
              this.renderContactMetadataValue(contactMetadataType, value)
            )}
          </View>
        );
        cardEntriesElements.push(view);
      }
    }

    return (
      <View key={contactMetadataType}>
        <Text>
          <Text style={styles.label}>contactMetadata</Text>
          <Text style={styles.value}> '{contactMetadataType}': </Text>
        </Text>
        <View>{cardEntriesElements}</View>
      </View>
    );
  }

  private valueTypeToString(valueType: ContactCardItemValueType) {
    switch (valueType) {
      case ContactCardItemValueType.Link:
        return "Link";
      case ContactCardItemValueType.PhoneNumber:
        return "PhoneNumber";
      case ContactCardItemValueType.Text:
        return "Text";
    }
  }

  private renderContactMetadataValue(
    contactMetadataType,
    value: ContactCardItemValue
  ) {
    const view = (
      <View key={value.displayText}>
        <Text>
          {" "}
          <Text style={styles.label}>- valueType:</Text>
          <Text style={styles.value}>
            {" "}
            {this.valueTypeToString(value.valueType)}
          </Text>
        </Text>
        <Text>
          {" "}
          <Text style={styles.label}>displayText:</Text>
          <Text style={styles.value}> '{value.displayText}'</Text>
        </Text>
        <Text>
          {" "}
          <Text style={styles.label}>contentDescription:</Text>
          <Text style={styles.value}> '{value.contentDescription}'</Text>
        </Text>
        {value.valueType === ContactCardItemValueType.Link ? (
          <Button
            onPress={() =>
              this.handleOpenContactMetadataLink(
                this.state.UserId,
                contactMetadataType,
                value.contentDescription
              )
            }
            title={Resource.getLocalizedString(
              "open_contact_metadata_link_btn_title"
            )}
            accessibilityLabel={Resource.getLocalizedString(
              "open_contact_metadata_link_accessibility_label"
            )}
          />
        ) : null}
      </View>
    );
    return view;
  }

  public handleOpenContactMetadataLink(
    UserId,
    contactMetadataType,
    contentDescription
  ) {
    PeopleProvider.openContactMetadataLink(
      UserId,
      contactMetadataType,
      contentDescription
    );
  }

  public handleViewProfilePictureForPerson(idType: IdType) {
    const UserId = this.UserIdFrom(
      this.props.currentUser,
      idType
    );

    PeopleProvider.viewProfilePictureForPerson(UserId)
      .then(() => {
        TraceLogger.logDebug(
          PeopleComponent.LOG_TAG,
          "View profile picture successfully."
        );
      })
      .catch((error) => {
        TraceLogger.logError(
          PeopleComponent.LOG_TAG,
          "Failed to view profile picture."
        );
        TraceLogger.logError(PeopleComponent.LOG_TAG, error);
        const message = Resource.getLocalizedString(
          "view_profile_picture_failure_message"
        );
        Utilities.showAlert(message);
      });
  }

  private UserIdFrom(user: User, userIdType: IdType): UserId {
    switch (userIdType) {
      case "mri":
        return { userIdType, userId: user.skypeMri };
      case "aadUpn":
        let upn = user.principalName;
        if (this.state.aliasSubstitution.length) {
          upn = this.state.aliasSubstitution + "@" + user.principalName.split("@")[1];
        }
        return { userIdType, userId: upn };
      case "aadObjectId":
        return { userIdType, userId: user.aadId };
    }
  }

  public handleGetPeopleDetailsBtnPress(idType: IdType) {
    const UserId = this.UserIdFrom(
      this.props.currentUser,
      idType
    );
    PeopleProvider.getPeopleDetails([UserId])
      .then((people: Person[]) => {
        TraceLogger.logDebug(PeopleComponent.LOG_TAG, "Received user details.");
        this.setState({ ...this.state, person: people[0] });
      })
      .catch((error) => {
        TraceLogger.logError(
          PeopleComponent.LOG_TAG,
          "Failed to receive user details."
        );
        TraceLogger.logError(PeopleComponent.LOG_TAG, error);
        const message = Resource.getLocalizedString(
          "get_people_details_failure_message"
        );
        Utilities.showAlert(message);
      });
  }

  public handleGetProfilePhotoBtnPress(idType: IdType) {
    const UserId = this.UserIdFrom(
      this.props.currentUser,
      idType
    );
    PeopleProvider.getProfilePictures([UserId])
      .then((pictures: PersonProfilePicture[]) => {
        TraceLogger.logDebug(
          PeopleComponent.LOG_TAG,
          "Received profile pictures."
        );
        this.setState({ ...this.state, personProfilePicture: pictures[0] });
      })
      .catch((error) => {
        TraceLogger.logError(
          PeopleComponent.LOG_TAG,
          "Failed to receive profile pictures."
        );
        TraceLogger.logError(PeopleComponent.LOG_TAG, error);
        const message = Resource.getLocalizedString(
          "get_profile_picture_failure_message"
        );
        Utilities.showAlert(message);
      });
  }

  public handleGetPresenceBtnPress(idType: IdType) {
    const UserId = this.UserIdFrom(
      this.props.currentUser,
      idType
    );
    PeopleProvider.getPresenceStatuses([UserId])
      .then((presenceStatuses: PersonPresenceStatus[]) => {
        TraceLogger.logDebug(PeopleComponent.LOG_TAG, "Received presence.");
        this.setState({
          ...this.state,
          personPresenceStatus: presenceStatuses[0],
        });
      })
      .catch((error) => {
        TraceLogger.logError(
          PeopleComponent.LOG_TAG,
          "Failed to receive presence."
        );
        TraceLogger.logError(PeopleComponent.LOG_TAG, error);
        const message = Resource.getLocalizedString(
          "get_presence_failure_message"
        );
        Utilities.showAlert(message);
      });
  }

  public handleGetContactMetadataBtnPress(idType: IdType) {
    const UserId = this.UserIdFrom(
      this.props.currentUser,
      idType
    );
    PeopleProvider.getContactMetadata([UserId])
      .then((contactMetadata: PersonContactMetadata[]) => {
        TraceLogger.logDebug(
          PeopleComponent.LOG_TAG,
          "Received contact metadata."
        );
        this.setState({
          ...this.state,
          UserId,
          personContactMetadata: contactMetadata[0],
        });
      })
      .catch((error) => {
        TraceLogger.logError(
          PeopleComponent.LOG_TAG,
          "Failed to receive contact metadata."
        );
        TraceLogger.logError(PeopleComponent.LOG_TAG, error);
        const message = Resource.getLocalizedString(
          "get_contact_metadata_failure_message"
        );
        Utilities.showAlert(message);
      });
  }
}
