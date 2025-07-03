import React from 'react'
import { Link } from 'react-router-dom'

const LoginPage = () => {
  return (
    <div>
      <p>Страница для входа</p>
      <Link to='/registration'>Регистрация</Link>
    </div>
  )
}

export default LoginPage