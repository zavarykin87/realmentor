import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = () => {
  return (
    <div className="home-page">
      <h1 className='text-red-700'>Добро пожаловать!</h1>
      <p>Это домашняя страница вашего приложения.</p>
      
      <div className="links">
        <Link to="/about" className="btn">О нас</Link>
        <Link to="/contact" className="btn">Контакты</Link>
      </div>
    </div>
  );
};

export default HomePage;