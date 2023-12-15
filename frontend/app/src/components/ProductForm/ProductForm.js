import React, { useState } from 'react';
import axios from 'axios';

const ProductForm = () => {
  const [productName, setProductName] = useState('');
  const [productValue, setProductValue] = useState('');
  const [productCategory, setProductCategory] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const productData = {
      name: productName,
      value: productValue,
      category: productCategory,
    };

    try {
      const response = await axios.post('http://localhost:8080/products', productData);

      if (response.status === 201) {
        console.log('Produto cadastrado com sucesso!');
        setProductName('');
        setProductValue('');
        setProductCategory('');
      } else {
        console.error('Erro ao cadastrar produto.');
      }
    } catch (error) {
      console.error('Erro na requisição:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Nome do Produto:
        <input
          type="text"
          value={productName}
          onChange={(e) => setProductName(e.target.value)}
        />
      </label>

      <label>
        Valor do Produto:
        <input
          type="text"
          value={productValue}
          onChange={(e) => setProductValue(e.target.value)}
        />
      </label>

      <label>
        Categoria:
        <input
          type="text"
          value={productCategory}
          onChange={(e) => setProductCategory(e.target.value)}
        />
      </label>

      <button type="submit">Cadastrar Produto</button>
    </form>
  );
};

export default ProductForm;
