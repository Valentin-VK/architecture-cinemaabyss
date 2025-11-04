FROM nginx:alpine
COPY nginx.conf /etc/nginx/nginx.conf
ENV MOVIES_SERVICE_PERCENTAGE=50
EXPOSE 8000
CMD ["sh", "-c", "sed -i \"s|\\${MOVIES_SERVICE_PERCENTAGE}|$MOVIES_SERVICE_PERCENTAGE|g\" /etc/nginx/nginx.conf && exec nginx -g \"daemon off;\""]