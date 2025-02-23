import express from 'express';
import cors from 'cors';
import dotenv from 'dotenv';
import connectDB from './config/connectDB.js';
import userRoutes from './routes/userRoutes.js'

dotenv.config();
connectDB();

const app = express();
const port = process.env.PORT || 4000;

// Middleware
app.use(cors());
app.use(express.json());
// Routes
app.use('/api/users', userRoutes);


app.get('/', (req, res) => {
  res.send("API Online 🚀");
});

app.listen(port, () =>
  console.log(`Server running on port ${port}, http://localhost:${port}`)
);