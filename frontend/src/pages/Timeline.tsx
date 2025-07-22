import React, { useState } from "react";
import {
  Container,
  Box,
  Typography,
  Card,
  CardContent,
  CardMedia,
  TextField,
  Button,
  Avatar,
  CardActions,
  IconButton,
} from "@mui/material";
import NavBar from "../Components/NavBar";
import reactCode from "../images/react.png";
import { Favorite, Share, Comment } from "@mui/icons-material";
import JohnDoe from "../images/JohnDoe.png";
import JaneSmith from "../images/JaneSmith.jpg";
import Design from "../images/DesignGrafico.jpg";

interface Post {
  id: number;
  user: string;
  avatar: string;
  content: string;
  image?: string;
  comments: CommentInterface[];
  likes: number;
}

interface CommentInterface {
  id: number;
  author: string;
  content: string;
}

const initialPosts: Post[] = [
  {
    id: 1,
    user: "John Doe",
    avatar: JohnDoe,
    content: "Aprendi novas habilidades em React!",
    image: reactCode,
    comments: [],
    likes: 10,
  },
  {
    id: 2,
    user: "Jane Smith",
    avatar: JaneSmith,
    content: "Alguém interessado em trocar habilidades de design gráfico?",
    image: Design,
    comments: [],
    likes: 5,
  },
];

const Timeline: React.FC = () => {
  const [posts, setPosts] = useState<Post[]>(initialPosts);
  const [newPostContent, setNewPostContent] = useState("");
  const [newPostImage, setNewPostImage] = useState<string | null>(null);

  const handlePostSubmit = () => {
    const newPost: Post = {
      id: 3,
      user: "Você",
      avatar: "/images/your-avatar.png",
      content: newPostContent,
      image: newPostImage || undefined,
      comments: [],
      likes: 0,
    };
    setPosts([newPost, ...posts]);
    setNewPostContent("");
    setNewPostImage(null);
  };

  const handleImageUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setNewPostImage(e.target?.result as string);
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };

  const handleLike = (postId: number) => {
    setPosts((prevPosts) =>
      prevPosts.map((post) =>
        post.id === postId ? { ...post, likes: post.likes + 1 } : post
      )
    );
  };

  const handleComment = (postId: number, comment: string) => {
    setPosts((prevPosts) =>
      prevPosts.map((post) =>
        post.id === postId
          ? {
              ...post,
              comments: [
                ...post.comments,
                {
                  id: post.comments.length + 1,
                  author: "You",
                  content: comment,
                },
              ],
            }
          : post
      )
    );
  };

  return (
    <section style={{ height: "100vh", overflowY: "auto" }}>
      <NavBar />
      <Container maxWidth="md">
        {/* Área de criação de post */}
        <Card sx={{ mt: 3, p: 2 }}>
          <Box display="flex" alignItems="center">
            <Avatar src="/images/your-avatar.png" sx={{ mr: 2 }} />
            <TextField
              variant="outlined"
              placeholder="O que você está pensando?"
              fullWidth
              multiline
              rows={2}
              value={newPostContent}
              onChange={(e) => setNewPostContent(e.target.value)}
            />
          </Box>
          <Box display="flex" justifyContent="space-between" mt={2}>
            <Button variant="contained" component="label">
              Upload de Imagem
              <input type="file" hidden onChange={handleImageUpload} />
            </Button>
            <Button
              variant="contained"
              color="primary"
              onClick={handlePostSubmit}
            >
              Postar
            </Button>
          </Box>
        </Card>

        {/* Área da timeline */}
        {posts.map((post, index) => (
          <Card key={index} sx={{ mt: 3 }}>
            <CardContent>
              <Box display="flex" alignItems="center">
                <Avatar src={post.avatar} sx={{ mr: 2 }} />
                <Typography variant="h6">{post.user}</Typography>
              </Box>
              <Typography variant="body1" sx={{ mt: 2 }}>
                {post.content}
              </Typography>
              {post.image && (
                <CardMedia
                  component="img"
                  image={post.image}
                  alt="Post image"
                  sx={{ mt: 2, borderRadius: "10px" }}
                />
              )}
            </CardContent>
            <CardActions>
              <IconButton onClick={() => handleLike(post.id)}>
                <Favorite /> {post.likes}
              </IconButton>
              <IconButton>
                <Comment />
              </IconButton>
              <IconButton>
                <Share />
              </IconButton>
            </CardActions>
            <CardContent>
              {post.comments.map((comment) => (
                <Box key={comment.id} sx={{ marginBottom: 2 }}>
                  <Typography variant="body2">
                    <strong>{comment.author}:</strong> {comment.content}
                  </Typography>
                </Box>
              ))}
              <TextField
                fullWidth
                variant="outlined"
                placeholder="Add a comment..."
                onKeyPress={(event) => {
                  if (event.key === "Enter") {
                    handleComment(
                      post.id,
                      (event.target as HTMLInputElement).value
                    );
                    (event.target as HTMLInputElement).value = "";
                  }
                }}
              />
            </CardContent>
          </Card>
        ))}
      </Container>
    </section>
  );
};

export default Timeline;
