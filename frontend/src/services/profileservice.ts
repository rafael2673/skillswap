import axios from 'axios';

const profileservice = axios.create({
  baseURL: "http://localhost:8082"
});


export default profileservice;