import axios from 'axios';

const authservice = axios.create({
  baseURL: "http://localhost:8081"
});


export default authservice;