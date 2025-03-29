import React, { useState } from "react";

function WishesGenerator() {
    const [wishes, setWishes] = useState('');


    const createWishes = async () => {
        try {
            const response = await fetch(`http://localhost:8080/wishes`)
            const data = await response.text();
            console.log(data);
            setWishes(data);
        } catch (error) {
            console.error("Error generating recipe : ", error)
        }
    };

    return (
        <div>
            <h2>Create Wishes</h2>
          

            <button onClick={createWishes}>Create Wishes</button>

            <div className="output">
                <pre className="recipe-text">{wishes}</pre>
            </div>
        </div>
    );
}

export default WishesGenerator;