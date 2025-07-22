import React, { useEffect, useState } from "react";
import {
  Container,
  Typography,
  Box,
  TextField,
  Button,
  Paper,
} from "@mui/material";
import NavBar from "../Components/NavBar";
import profileservice from "../services/profileservice";
import LinearIndeterminate from "../Components/LinearIndeterminate";
import { AxiosResponse } from "axios";

interface Profile {
  profileId: number;
  firstName: String;
  lastName: String;
  bio?: String;
  pictureUrl?: String;
  updatedAt?: String;
  userId: number;
  addressId?: {
    address_id?: number;
    street?: String;
    number?: String;
    zipCode?: String;
    state?: String;
    country?: String;
  };
}

const ProfilePage: React.FC = () => {
  const [profile, setProfile] = useState<Profile | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedProfile, setEditedProfile] = useState<Profile | null>(null);
  const token = sessionStorage.getItem("accessToken");

  useEffect(() => {
    if (!token) return;

    profileservice
      .get("/profile", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const { data }: AxiosResponse<Profile, null> = response;
        setProfile(data);
        setEditedProfile(data); // Inicializa o editedProfile com os dados do perfil
      })
      .catch((error) => {
        console.error(error);
      });
  }, [token]);

  const handleEditChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (["street", "number", "zipCode", "state", "country"].includes(name)) {
      setEditedProfile((prevProfile: Profile | null) => {
        if (prevProfile) {
          return {
            ...prevProfile,
            addressId: {
              ...prevProfile.addressId,
              [name]: value,
            },
          };
        }
        return prevProfile;
      });
    } else {
      setEditedProfile((prevProfile: Profile | null) => {
        if (prevProfile) {
          return {
            ...prevProfile,
            [name]: value,
          };
        }
        return prevProfile;
      });
    }
  };

  const handleSave = () => {
    if (!editedProfile) return;

    const updatedProfile = {
      profileId: editedProfile.profileId,
      firstName: editedProfile.firstName,
      lastName: editedProfile.lastName,
      bio: editedProfile.bio,
      userId: editedProfile.userId,
      street: editedProfile.addressId?.street || "", // Use empty string if undefined
      number: editedProfile.addressId?.number || "",
      zipCode: editedProfile.addressId?.zipCode || "",
      state: editedProfile.addressId?.state || "",
      country: editedProfile.addressId?.country || "",
    };

    profileservice
      .put("/profile", updatedProfile, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        setProfile(editedProfile);
        setIsEditing(false);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  if (!profile) {
    return <LinearIndeterminate />;
  }

  return (
    <section style={{ height: "100vh" }}>
      <NavBar />
      <Container>
        <Box mt={3}>
          <Typography variant="h4" gutterBottom>
            Profile
          </Typography>
          <Paper
            elevation={3}
            style={{ padding: "20px", borderRadius: "10px" }}
          >
            <Typography variant="h6" gutterBottom>
              Personal Information
            </Typography>
            <TextField
              label="Nome"
              name="firstName"
              value={editedProfile?.firstName || ""}
              onChange={handleEditChange}
              fullWidth
              margin="normal"
              disabled={!isEditing}
            />
            <TextField
              label="Sobrenome"
              name="lastName"
              value={editedProfile?.lastName || ""}
              onChange={handleEditChange}
              fullWidth
              margin="normal"
              disabled={!isEditing}
            />
            <TextField
              label="Bio"
              name="bio"
              value={editedProfile?.bio || ""}
              onChange={handleEditChange}
              fullWidth
              margin="normal"
              disabled={!isEditing}
            />

            <>
              <Typography
                variant="h6"
                gutterBottom
                style={{ marginTop: "20px" }}
              >
                Address Information
              </Typography>
              <TextField
                label="Rua"
                name="street"
                value={editedProfile?.addressId?.street || ""}
                onChange={handleEditChange}
                fullWidth
                margin="normal"
                disabled={!isEditing}
              />
              <TextField
                label="Número"
                name="number"
                value={editedProfile?.addressId?.number || ""}
                onChange={handleEditChange}
                fullWidth
                margin="normal"
                disabled={!isEditing}
              />
              <TextField
                label="CEP"
                name="zipCode"
                value={editedProfile?.addressId?.zipCode || ""}
                onChange={handleEditChange}
                fullWidth
                margin="normal"
                disabled={!isEditing}
              />
              <TextField
                label="Estado"
                name="state"
                value={editedProfile?.addressId?.state || ""}
                onChange={handleEditChange}
                fullWidth
                margin="normal"
                disabled={!isEditing}
              />
              <TextField
                label="País"
                name="country"
                value={editedProfile?.addressId?.country || ""}
                onChange={handleEditChange}
                fullWidth
                margin="normal"
                disabled={!isEditing}
              />
            </>
            {isEditing ? (
              <Button
                variant="contained"
                color="primary"
                onClick={handleSave}
                style={{ marginTop: "20px" }}
              >
                Save
              </Button>
            ) : (
              <Button
                variant="contained"
                color="primary"
                onClick={() => setIsEditing(true)}
                style={{ marginTop: "20px" }}
              >
                Edit
              </Button>
            )}
          </Paper>
        </Box>
      </Container>
    </section>
  );
};

export default ProfilePage;
