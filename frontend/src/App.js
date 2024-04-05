import './App.css';
import {useLocalState} from "./util/useLocalStorage";
import {useEffect} from "react";

function App() {
    const [jwt, setJwt] = useLocalState("","jwt");

    useEffect(() => {
        if(!jwt){
            const reqBody = {
                username: "petowner",
                password: "asdfasdf",
            };
            fetch("api/auth/login", {
                headers: {
                    "Content-Type": "application/json"
                },
                method: "post",
                body: JSON.stringify(reqBody),
            })
                .then((response) => Promise.all([response.json(), response.headers]))
                .then(([body, headers]) => {
                        setJwt(headers.get("authorization"));
                    });
        }
    }, []);



    return(
      <div className="App">
        <h1>Jwt values is {jwt}</h1>
      </div>
  );
}

export default App;
