import { createContext } from "react";


const AuthContext = createContext({
    auth: false,
    setAuth: () => { },
    setAccountCreated: () => { },
    accountCreated: false,
});

export default AuthContext;