import React from 'react'
import { Link } from 'react-router-dom'
import Header from '../components/Header'

const HomePage = () => {
  return (
    <div>
      <Header />
      <p>Домашняя страница</p>
      <ul>
        <li>
          <Link to='/login'>Войти</Link>
          <Link to='/logout'>Выйти</Link>
        </li>
      </ul>
    </div>
  )
}

export default HomePage