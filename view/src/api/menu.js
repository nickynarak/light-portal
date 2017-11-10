const body = {
  host: 'lightapi.net',
  service: 'menu',
  action: 'getMenuByHost',
  version: '0.1.0',
  data: { host: 'example.org' },
};

/**
 * Returns a promise from Menu Service
 * @returns Promise
 */
export default async () => {
  const res = await fetch('/api/rest', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(body),
  });
  return res.json();
};
