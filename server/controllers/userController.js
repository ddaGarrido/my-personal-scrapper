import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";
import User from "../models/User.js";

export const register = async (req, res) => {
    try {
        const { username, password } = req.body;

        if (!username || !password) {
            return res.status(400).json({ message: "Fields are required" });
        }

        if (await User.findOne({ username })) {
            return res.status(400).json({ message: "Username already exists" });
        }

        const hashedPassword = await bcrypt.hash(password, 10);
        const newUser = new User({ username, password: hashedPassword });

        res.status(201).json({message: "User created"});
    } catch (error) {
        res.status(500).json({ message: "Internal server error" });
    }
};

export const login = async (req, res) => {
    try {
        const { username, password } = req.body;

        if (!username || !password) {
            return res.status(400).json({ message: "Fields are required" });
        }

        const user = await User.findOne({ username });

        if (!user) {
            return res.status(401).json({ message: "Invalid cresadentials" });
        }

        if (await bcrypt.compare(password, user.password)) {
            return res.status(401).json({ message: "Invalid cresadfasdfasdfdentials" });
        }

        const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET, { expiresIn: "1h" });

        res.status(200).json({ token });
    } catch (error) {
        res.status(500).json({ message: "Internal server error" });
    }
};

export const getUserProfile = async (req, res) => {
    try {
        const user = await User.findById(req.user.id).select("-password");
        res.json(user);
    } catch (error) {
        console.log(error);
        res.status(500).json({ message: "Internal server error" });
    }
}