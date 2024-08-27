import React, { useState } from "react";
import {
  Container,
  Grid,
  Card,
  CardContent,
  Typography,
  Avatar,
  Rating,
  Pagination,
} from "@mui/material";
import NavBar from "../Components/NavBar";
import JohnDoe from "../images/JohnDoe.png";
import JaneSmith from "../images/JaneSmith.jpg";


interface User {
  name: string;
  avatar: string;
  rating: number;
  skills: string[];
  learning: string[];
}

const users: User[] = [
  {
    name: "John Doe",
    avatar: JohnDoe,
    rating: 4.8,
    skills: ["JavaScript", "Node.js"],
    learning: ["TypeScript", "GraphQL", "React"],
  },
  {
    name: "Jane Smith",
    avatar: JaneSmith,
    rating: 4.6,
    skills: ["Python", "Data Science", "Machine Learning"],
    learning: ["Rust", "Web Development"],
  },
  {
    name: "Jane Smith",
    avatar: JaneSmith,
    rating: 4.6,
    skills: ["Python", "Data Science", "Machine Learning"],
    learning: ["Rust", "Web Development"],
  },
  {
    name: "Jane Smith",
    avatar: JaneSmith,
    rating: 4.6,
    skills: ["Python", "Data Science", "Machine Learning"],
    learning: ["Rust", "Web Development"],
  },
  {
    name: "Jane Smith",
    avatar: JaneSmith,
    rating: 4.6,
    skills: ["Python", "Data Science", "Machine Learning", "Designer Gráfico"],
    learning: ["Rust", "Web Development"],
  },
  {
    name: "Jane Smith",
    avatar: JaneSmith,
    rating: 4.6,
    skills: ["Python", "Data Science", "Machine Learning"],
    learning: ["Rust", "Web Development"],
  },
  // Adicione mais usuários conforme necessário
];

const Match: React.FC = () => {
  const [page, setPage] = useState(1);
  const itemsPerPage = 4;

  const handlePageChange = (_: React.ChangeEvent<unknown>, value: number) => {
    setPage(value);
  };

  const paginatedUsers = users.slice(
    (page - 1) * itemsPerPage,
    page * itemsPerPage
  );

  return (
    <section style={{ height: "100vh" }}>
      <NavBar />
      <Container sx={{paddingTop: 20}}>
        <Grid container spacing={4}>
          {paginatedUsers.map((user, index) => (
            <Grid item xs={12} sm={6} md={4} lg={3} key={index}>
              <Card
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  padding: "1rem",
                  boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                }}
              >
                <Avatar
                  src={user.avatar}
                  alt={user.name}
                  sx={{ width: 80, height: 80, marginBottom: "1rem" }}
                />
                <Typography variant="h6">{user.name}</Typography>
                <Rating
                  value={user.rating}
                  precision={0.1}
                  readOnly
                  sx={{ marginBottom: "1rem" }}
                />
                <CardContent>
                  <Typography variant="subtitle1">Skills:</Typography>
                  <Typography variant="body2" color="text.secondary">
                    {user.skills.join(", ")}
                  </Typography>
                  <Typography variant="subtitle1" sx={{ marginTop: "0.5rem" }}>
                    Learning:
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {user.learning.join(", ")}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
        <Pagination
          count={Math.ceil(users.length / itemsPerPage)}
          page={page}
          onChange={handlePageChange}
          sx={{ marginTop: "2rem", display: "flex", justifyContent: "center" }}
        />
      </Container>
    </section>
  );
};

export default Match;
