package com.epam.esm.exception;

/**
 * Exception for entity already exists
 */
public class EntityAlreadyExistsException  extends RuntimeException {

        private String message;

        @Override
        public String getMessage() {
            return message;
        }

        /**
         * Constructor
         *
         * @param message the message
         */
        public EntityAlreadyExistsException(String message) {

            this.message = message;
        }
}
