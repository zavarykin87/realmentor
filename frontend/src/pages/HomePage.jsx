import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = () => {
  return (
    <div>
      <h1>Добро пожаловать!</h1>
      <p>Это домашняя страница вашего приложения.</p>
      
      <div>
        <Link to="/about">О нас</Link>
        <Link to="/contact">Контакты</Link>
      </div>
    </div>
  );
};

export default HomePage;