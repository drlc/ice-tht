import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Login', () => {
  render(<App />);
  const titleLoginElement = screen.getByTitle(/Login/i);
  expect(titleLoginElement).toBeInTheDocument();
  const buttonLoginElement = screen.getByRole('button', { name: /Login/i });
  expect(buttonLoginElement).toBeInTheDocument();
});
