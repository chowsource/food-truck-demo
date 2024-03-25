import React, { useState, useEffect,memo } from 'react';
import axios from 'axios';

function App() {
    const [foodTrucks, setFoodTrucks] = useState([]);

    useEffect(() => {
        const axiosInstance = axios.create({
            baseURL: 'http://localhost:8080'
        });

        axiosInstance.get('/api/foodtrucks/getAll').then(response => {
                console.log(response.data);
                setFoodTrucks(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }, []);

    return (
        <div id="root">
            <h1>Food Trucks Demo</h1>
            <ul>
                {foodTrucks.map(truck => (
                    <li key={truck.locationid}>{truck.Applicant} - {truck.Location}-{truck.Address}</li>
                ))}
            </ul>
        </div>
    );
}

export default App;

